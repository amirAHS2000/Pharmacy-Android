package com.example.pharmacyapp.util.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.pharmacyapp.model.NonMedicine

class NonMedicineDiffCallBack : DiffUtil.ItemCallback<NonMedicine>() {
    override fun areItemsTheSame(oldItem: NonMedicine, newItem: NonMedicine): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NonMedicine, newItem: NonMedicine): Boolean {
        return oldItem == newItem
    }
}
