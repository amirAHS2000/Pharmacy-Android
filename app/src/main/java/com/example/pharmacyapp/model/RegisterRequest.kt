package com.example.pharmacyapp.model

data class RegisterRequest(
    val ref_id: Int,
    val password: String,
    val phone: String,
    val nationalNumber: String,
    val type: String,
)