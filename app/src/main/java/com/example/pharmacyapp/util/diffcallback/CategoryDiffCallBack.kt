package com.example.pharmacyapp.util.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.pharmacyapp.model.category.CategoriesResult
import com.example.pharmacyapp.model.category.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<CategoriesResult>() {
    override fun areItemsTheSame(oldItem: CategoriesResult, newItem: CategoriesResult): Boolean {
        return oldItem.category.id == newItem.category.id
    }

    override fun areContentsTheSame(oldItem: CategoriesResult, newItem: CategoriesResult): Boolean {
        return oldItem == newItem
    }
}