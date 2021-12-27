package com.example.pharmacyapp.data.network

import com.example.pharmacyapp.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PharmacyApi {
    //sign-up and login (prepare)
    @POST("api/login/patient")
    suspend fun login(
        @Query("phone") phone: String,
        @Query("password") password: String,
    ): Response<LoginResponse>

    @POST("api/register")
    suspend fun signUp(
        @Query("ref_id") ref_id: Int,
        @Query("phone") phone: String,
        @Query("nat_num") nationalNumber: String,
        @Query("password") password: String,
        @Query("type") type: String,
    ): Response<LoginResponse>

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

    @POST("api/user/find")
    suspend fun getUserInfoByPhone(
        @Query("phone") phone: String
    ): Response<UserResponse>

    @POST("api/register/patient")
    suspend fun createPatient(
        @Query("first_name") firstName: String,
        @Query("last_name") lastName: String,
        @Query("nat_num") nationalNumber: String,
        @Query("phone") phone: String,
        @Query("ins_num") insuranceNumber: String?,
        @Query("ins_id") insuranceId: Int?,
    ): Response<CreatePatientResponse>
}