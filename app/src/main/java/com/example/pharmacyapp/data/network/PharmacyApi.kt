package com.example.pharmacyapp.data.network

import com.example.pharmacyapp.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PharmacyApi {
    //sign-up and login (prepare)
    @POST("api/login")
    suspend fun login(
        @Query("phone") phone: String,
        @Query("password") password: String,
    ): Response<LoginResponse>

    @POST("api/signUp")
    suspend fun signUp(
        @Query("firstname") firstname: String,
        @Query("lastname") lastname: String,
        @Query("nationalNumber") nationalNumber: String,
        @Query("phoneNumber") phoneNumber: String,
        @Query("password") password: String,
    ): Response<UserAndToken>
    // TODO: 12/21/2021 change signUp return type (create a model for sign-up)

    //get category and medicine (home)
    @GET("api/")
    suspend fun getMedicine(
        @Query("medName") medicineName: String,
    ): Response<List<Medicine>>

    @GET("api/")
    suspend fun getCategory(): Response<List<Category>>

    //get user information (profile)
    @GET("api/")
    suspend fun getUserInfo(): Response<User>
}