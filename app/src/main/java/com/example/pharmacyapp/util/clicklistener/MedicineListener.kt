package com.example.pharmacyapp.util.clicklistener

import com.example.pharmacyapp.model.category.MedicineInCategoryModel

class MedicineListener(val clickListener: (medicineID: Int) -> Unit) {
    fun onClick(medicine: MedicineInCategoryModel) = clickListener(medicine.med.id)
}