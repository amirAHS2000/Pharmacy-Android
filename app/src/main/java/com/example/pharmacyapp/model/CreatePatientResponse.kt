package com.example.pharmacyapp.model

data class CreatePatientResponse(
    val message: List<String>,
    val result: List<PatientResult>,
    val status: Boolean,
)