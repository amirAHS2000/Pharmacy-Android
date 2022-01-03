package com.example.pharmacyapp.util

import retrofit2.Response

fun <T> Response<T>.handle(): NetworkResult<T> {
    return when {
        message().toString().contains("timeout") -> NetworkResult.Error("Timeout")
        code() == 402 -> NetworkResult.Error("API Key Limited")
        code() == 404 -> NetworkResult.Error("Can't Find Server")
        body() == null -> NetworkResult.Error("Not Found")
        isSuccessful -> NetworkResult.Success(body()!!)
        else -> NetworkResult.Error(message())
    }
}