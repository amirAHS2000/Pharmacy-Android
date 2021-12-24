package com.example.pharmacyapp.model.network

data class PatientNetwork(
    val first_name: String,
    val last_name: String,
    val id: Int,
    val ins_id: Int?,
    val ins_num: String?,
    val nat_num: String,
    val phone: String,
    val created_at: String,
    val updated_at: String,
)