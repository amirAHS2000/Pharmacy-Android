package com.example.pharmacyapp.model.user


data class UserInformationResponse(
    val message: List<String> = listOf(),
    val result: List<UserInformationResult> = listOf(),
    val status: Boolean
)
