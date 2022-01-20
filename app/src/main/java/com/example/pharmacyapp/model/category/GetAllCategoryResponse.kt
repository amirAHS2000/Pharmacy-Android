package com.example.pharmacyapp.model.category

data class GetAllCategoryResponse(
    val message: List<String> = listOf(),
    val result: List<CategoriesResult> = listOf(),
    val status: Boolean
)