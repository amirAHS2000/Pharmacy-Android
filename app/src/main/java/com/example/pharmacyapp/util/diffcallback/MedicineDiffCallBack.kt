package com.example.pharmacyapp.util.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.category.MedicineInCategoryModel

class MedicineDiffCallback : DiffUtil.ItemCallback<MedicineInCategoryModel>() {
    override fun areItemsTheSame(oldItem: MedicineInCategoryModel, newItem: MedicineInCategoryModel): Boolean {
        return oldItem.med.id == newItem.med.id
    }

    override fun areContentsTheSame(oldItem: MedicineInCategoryModel, newItem: MedicineInCategoryModel): Boolean {
        return oldItem == newItem
    }
}