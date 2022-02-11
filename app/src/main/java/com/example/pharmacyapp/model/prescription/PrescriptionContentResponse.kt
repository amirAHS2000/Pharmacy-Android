package com.example.pharmacyapp.model.prescription

data class PrescriptionContentResponse(
    val message: List<String>,
    val result: PrescriptionContentResult,
    val status: Boolean
)
