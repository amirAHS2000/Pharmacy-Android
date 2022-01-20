package com.example.pharmacyapp.model.medicine

import com.example.pharmacyapp.model.company.Company
import com.example.pharmacyapp.model.medicine.Med
import com.example.pharmacyapp.model.medicine.Pharm

data class FullInfoMedModel(
    val company: Company,
    val image: String,
    val med: Med,
    val pharm: Pharm
)