package com.example.pharmacyapp.data

import com.example.pharmacyapp.model.LoginResponse
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
    fun findUserByPhone(phone: String): Boolean {
//        remoteDataSource.findUserByPhone()
        return true // TODO implement this
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

    val remote = remoteDataSource // TODO remove this


}