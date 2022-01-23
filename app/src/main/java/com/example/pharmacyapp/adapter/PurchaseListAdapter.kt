package com.example.pharmacyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.databinding.PurchaseListItemBinding
import com.example.pharmacyapp.model.category.MedicineInCategoryModel
import com.example.pharmacyapp.util.diffcallback.MedicineDiffCallback

class PurchaseListAdapter() :
    ListAdapter<MedicineInCategoryModel, PurchaseListAdapter.ViewHolder>(MedicineDiffCallback()) {

    class ViewHolder private constructor(
        private val binding: PurchaseListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            product: MedicineInCategoryModel?,
        ) {
            binding.product = product
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PurchaseListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}