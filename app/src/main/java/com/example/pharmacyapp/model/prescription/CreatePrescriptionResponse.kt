package com.example.pharmacyapp.model.prescription

data class CreatePrescriptionResponse(
    val message: List<String>,
    val prescription: Prescription,
    val status: Boolean
)