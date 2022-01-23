package com.example.pharmacyapp.model.user

data class UserInformation(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val nat_num: String,
    val phone: String,
    val ins_num: Int?,
    val ins_id: Int?,
    val created_at: String,
    val updated_at: String
)