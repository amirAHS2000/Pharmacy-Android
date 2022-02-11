package com.example.pharmacyapp.model.prescription

import com.example.pharmacyapp.model.medicine.Med

data class PrescriptionContentResult(
    val content: Content,
    val med: Med
)
