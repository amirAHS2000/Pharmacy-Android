package com.example.pharmacyapp.model

data class Medicine(
    val id: Int,
    val name: String,
    val imageUri: String,
    val price: Int,
    val stock: Int,
    val company: String,
    val usage: String,
    val keeping: String,
    val guide: String,
    val need_dr: Boolean,
    val images: List<Photo>
)