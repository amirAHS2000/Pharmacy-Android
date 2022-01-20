package com.example.pharmacyapp.model.medicine

data class GetMedicineResponse(
    val message: List<String> = listOf(),
    val result: FullInfoMedModel,
    val status: Boolean
)