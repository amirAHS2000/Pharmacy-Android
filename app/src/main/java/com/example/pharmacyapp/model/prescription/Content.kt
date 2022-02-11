package com.example.pharmacyapp.model.prescription

data class Content(
    val presc_id: Int,
    val ins_buy: Boolean,
    val med_id: Int,
    val price: Int,
    val updated_at: String,
    val created_at: String,
    val id: Int
)