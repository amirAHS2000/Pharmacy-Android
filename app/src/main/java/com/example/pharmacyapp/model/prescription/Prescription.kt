package com.example.pharmacyapp.model.prescription

data class Prescription(
    val created_at: String,
    val date: String,
    val doctor: String,
    val id: Int,
    val patient_id: Int,
    val total_price: Any,
    val updated_at: String
)