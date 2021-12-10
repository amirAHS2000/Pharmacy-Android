package com.example.pharmacyapp.network

import com.example.pharmacyapp.network.models.UserAndToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface Service {
    @POST("api/login")
    suspend fun login(
            @Query("phone") phone: String,
            @Query("password") password: String
    ): UserAndToken
}


object PharmacyNetwork {
    private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://5.56.132.82:8080/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    val pharmacy = retrofit.create(Service::class.java)
}