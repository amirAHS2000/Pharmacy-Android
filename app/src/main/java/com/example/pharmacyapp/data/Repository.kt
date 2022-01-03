package com.example.pharmacyapp.data

import com.example.pharmacyapp.model.LoginResponse
import com.example.pharmacyapp.model.UserResponse
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    val dataStore: DataStoreRepository
) {
    suspend fun findUserByPhone(phone: String, nationalNumber: String): Response<UserResponse> {
        return remoteDataSource.findUserByPhone(phone, nationalNumber)
    }

    suspend fun login(phone: String, password: String): Response<LoginResponse> {
        return remoteDataSource.login(phone, password)
    }

    suspend fun signUp(
        firstName: String,
        lastName: String,
        nationalNumber: String,
        phone: String,
        password: String,
        insuranceNumber: String? = null,
        insuranceId: Int? = null,
    ): Response<LoginResponse> {
        val patient = remoteDataSource.createPatient(firstName,
            lastName,
            nationalNumber,
            phone,
            insuranceNumber,
            insuranceId)
        return when {
            !patient.isSuccessful -> Response.error(patient.code(), patient.errorBody()!!)

            patient.body()?.status == false -> Response.success(LoginResponse(status = false,
                message = patient.body()!!.message))

            else -> {
                val refId = patient.body()?.result?.first()?.patient?.id!!
                remoteDataSource.signUp(refId, nationalNumber, phone, password, "patient")
            }
        }

    }

    suspend fun resetPassword(id: Int, password: String) : Response<UserResponse> {
        return remoteDataSource.resetPassword(id, password)
    }

    val remote = remoteDataSource // TODO remove this

    suspend fun saveUserLocally(id: Int, token: String): Boolean {
        return dataStore.saveUserData(id, token)
    }

}