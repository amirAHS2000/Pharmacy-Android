package com.example.pharmacyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.databinding.CategoryListItemBinding
import com.example.pharmacyapp.model.Category

class CategoryAdapter(private val clickListener: CategoryListener) :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiffCallback()) {
    class ViewHolder private constructor(
        private val binding: CategoryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            result: Category?,
            clickListener: CategoryListener
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

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}

class CategoryListener(val clickListener: (categoryID: Int) -> Unit) {
    fun onClick(category: Category) = clickListener(category.id)
}
