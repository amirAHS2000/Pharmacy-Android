package com.example.pharmacyapp.util.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.pharmacyapp.model.Medicine

class MedicineDiffCallback : DiffUtil.ItemCallback<Medicine>() {
    override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem == newItem
    }
}