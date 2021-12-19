package com.example.pharmacyapp.network.models

data class LoginResponse(
    val message: List<String>,
    val result: List<UserAndToken>,
    val status: Boolean
)