package com.example.pharmacyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.databinding.CategoryListItemBinding
import com.example.pharmacyapp.model.category.CategoriesResult
import com.example.pharmacyapp.model.category.Category
import com.example.pharmacyapp.util.clicklistener.CategoryListener
import com.example.pharmacyapp.util.diffcallback.CategoryDiffCallback

class CategoryAdapter(private val clickListener: CategoryListener) :
    ListAdapter<CategoriesResult, CategoryAdapter.ViewHolder>(CategoryDiffCallback()) {
    class ViewHolder private constructor(
        private val binding: CategoryListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            result: CategoriesResult?,
            clickListener: CategoryListener,
        ) {
            binding.category = result
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategoryListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}