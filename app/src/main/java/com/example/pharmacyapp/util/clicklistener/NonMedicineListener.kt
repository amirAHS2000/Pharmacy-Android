package com.example.pharmacyapp.util.clicklistener

import com.example.pharmacyapp.model.NonMedicine

class NonMedicineListener(val clickListener: (nonMedicineId: Int) -> Unit) {
    fun onclick(nonMedicine: NonMedicine) = clickListener(nonMedicine.id)
}