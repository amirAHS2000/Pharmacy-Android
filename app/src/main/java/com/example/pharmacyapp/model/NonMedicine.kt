package com.example.pharmacyapp.model

data class NonMedicine(
    val id: Int,
    val name: String,
    val imageUri: String,
    val price: Int,
    val stock: Int
)
