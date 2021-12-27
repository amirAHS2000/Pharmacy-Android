package com.example.pharmacyapp.data

import com.example.pharmacyapp.data.network.PharmacyApi
import com.example.pharmacyapp.model.*
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val pharmacyApi: PharmacyApi,
) {
    suspend fun login(phoneNumber: String, password: String): Response<LoginResponse> {
        return pharmacyApi.login(phoneNumber, password)
    }

    suspend fun signUp(
        ref_id: Int,
        nationalNumber: String,
        phone: String,
        password: String,
        type: String,
    ): Response<LoginResponse> {
        return pharmacyApi.signUp(ref_id, phone, nationalNumber, password, type)
    }

    suspend fun createPatient(
        firstName: String,
        lastName: String,
        nationalNumber: String,
        phone: String,
        insuranceNumber: String?,
        insuranceId: Int?,
    ): Response<CreatePatientResponse> {
        return pharmacyApi.createPatient(firstName,
            lastName,
            nationalNumber,
            phone,
            insuranceNumber,
            insuranceId)
    }

    //get data like medicines - categories
    suspend fun getMedicine(medName: String): Response<List<Medicine>> {
        return pharmacyApi.getMedicine(medName)
    }

    suspend fun getCategory(): Response<List<Category>> {
        return pharmacyApi.getCategory()
    }

    //get user information
    suspend fun getUserInfo(): Response<User> {
        return pharmacyApi.getUserInfo()
    }

    suspend fun findUserByPhone(phone: String): Response<UserResponse> {
        return pharmacyApi.getUserInfoByPhone(phone)
    }
}