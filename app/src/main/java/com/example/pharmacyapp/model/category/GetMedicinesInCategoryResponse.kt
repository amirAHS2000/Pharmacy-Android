package com.example.pharmacyapp.model.category

data class GetMedicinesInCategoryResponse(
    val message: List<String> = listOf(),
    val result: List<MedicineInCategoryModel> = listOf(),
    val status: Boolean
)
