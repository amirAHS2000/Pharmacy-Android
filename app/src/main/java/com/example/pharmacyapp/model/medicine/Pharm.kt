package com.example.pharmacyapp.model.medicine

data class Pharm(
    val cat_id: String,
    val created_at: String,
    val guide: String,
    val id: Int,
    val keeping: String,
    val name: String,
    val need_dr: Boolean,
    val updated_at: String,
    val usage: String
)
