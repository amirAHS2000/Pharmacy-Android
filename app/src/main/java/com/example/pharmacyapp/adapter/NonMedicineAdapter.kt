package com.example.pharmacyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.databinding.NonMedicineListItemBinding
import com.example.pharmacyapp.model.NonMedicine
import com.example.pharmacyapp.util.clicklistener.NonMedicineListener
import com.example.pharmacyapp.util.diffcallback.NonMedicineDiffCallBack

class NonMedicineAdapter(private val clickListener: NonMedicineListener) :
    ListAdapter<NonMedicine, NonMedicineAdapter.ViewHolder>(NonMedicineDiffCallBack()) {
    class ViewHolder private constructor(
        private val binding: NonMedicineListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            nonMedicine: NonMedicine?,
            clickListener: NonMedicineListener
        ) {
            binding.nonMedicine = nonMedicine
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NonMedicineListItemBinding.inflate(layoutInflater, parent, false)
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
