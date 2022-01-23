package com.example.pharmacyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.databinding.MedicineLargeItemBinding
import com.example.pharmacyapp.databinding.MedicineRowListItemBinding
import com.example.pharmacyapp.model.category.MedicineInCategoryModel
import com.example.pharmacyapp.model.medicine.FullInfoMedModel
import com.example.pharmacyapp.util.clicklistener.MedicineListener
import com.example.pharmacyapp.util.diffcallback.MedicineDiffCallback
import com.example.pharmacyapp.util.diffcallback.MedicineLargeDiffCallBack

class MedicineLargeAdapter() :
    ListAdapter<FullInfoMedModel, MedicineLargeAdapter.ViewHolder>(MedicineLargeDiffCallBack()) {

    class ViewHolder private constructor(
        private val binding: MedicineLargeItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            medicine: FullInfoMedModel?,
        ) {
            binding.medicine = medicine
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MedicineLargeItemBinding.inflate(layoutInflater, parent, false)
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