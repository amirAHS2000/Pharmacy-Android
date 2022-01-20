package com.example.pharmacyapp.model.medicine

data class Med(
    val add_info: String,
    val comp_id: String,
    val created_at: String,
    val exp_date: String,
    val id: Int,
    val img_path: String,
    val inv: Int,
    val pharm_id: String,
    val price: Int,
    val updated_at: String
)
