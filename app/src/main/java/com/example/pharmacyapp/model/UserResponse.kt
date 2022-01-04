package com.example.pharmacyapp.model

data class UserResponse(
    val message: List<String> = listOf(),
    val result: List<User> = listOf(),
    val status: Boolean
)