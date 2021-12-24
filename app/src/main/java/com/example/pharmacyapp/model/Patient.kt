package com.example.pharmacyapp.model

data class Patient(
    val first_name: String,
    val last_name: String,
    val id: Int,
    val ins_id: Int?,
    val ins_num: String?,
    val nat_num: String,
    val phone: String,
)
