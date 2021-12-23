package com.example.pharmacyapp.model

data class LoginResponse(
    val message: List<String>,
    val result: List<UserAndToken>,
    val status: Boolean,
)