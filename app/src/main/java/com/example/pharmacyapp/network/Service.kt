package com.example.pharmacyapp.network

import com.example.pharmacyapp.network.models.UserAndToken
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface Service {
    @POST("api/login")
    suspend fun login(
        @Query("phone") phone: String,
        @Query("password") password: String
    )
}

object PharmacyNetwork {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://5.56.132.82:8080/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val pharmacy = retrofit.create(Service::class.java)
}