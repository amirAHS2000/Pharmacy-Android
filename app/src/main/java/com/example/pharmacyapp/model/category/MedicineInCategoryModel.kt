package com.example.pharmacyapp.model.category

import com.example.pharmacyapp.model.medicine.Med
import com.example.pharmacyapp.model.medicine.Pharm

data class MedicineInCategoryModel(
    val med: Med,
    val pharm: Pharm,
    val image: String
)
