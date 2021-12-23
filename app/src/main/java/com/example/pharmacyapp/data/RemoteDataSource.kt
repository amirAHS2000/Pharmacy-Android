package com.example.pharmacyapp.data

import com.example.pharmacyapp.data.network.PharmacyApi
import com.example.pharmacyapp.model.Category
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.User
import com.example.pharmacyapp.network.models.LoginResponse
import com.example.pharmacyapp.network.models.UserAndToken
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val pharmacyApi: PharmacyApi
) {
    suspend fun login(phoneNumber: String, password: String): Response<LoginResponse> {
        return pharmacyApi.login(phoneNumber, password)
    }

    suspend fun signUp(
        firstname: String,
        lastname: String,
        nationalNumber: String,
        phoneNumber: String,
        password: String
    ): Response<UserAndToken> {
        return pharmacyApi.signUp(firstname, lastname, nationalNumber, phoneNumber, password)
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
}