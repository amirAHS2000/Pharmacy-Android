package com.example.pharmacyapp.data.network

import com.example.pharmacyapp.model.Category
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.network.models.UserAndToken
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PharmacyApi {
    @POST("api/login")
    suspend fun login(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Response<UserAndToken>

    @GET("api/")
    suspend fun getMedicine(
        @Query("medName") medicineName: String
    ): Response<List<Medicine>>

    @GET("api/")
    suspend fun getCategory(): Response<List<Category>>
}