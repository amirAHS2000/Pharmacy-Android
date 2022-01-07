package com.example.pharmacyapp.util.clicklistener

import com.example.pharmacyapp.model.Medicine

class MedicineListener(val clickListener: (medicineID: Int) -> Unit) {
    fun onClick(medicine: Medicine) = clickListener(medicine.id)
}