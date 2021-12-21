package com.example.pharmacyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.databinding.CategoryListItemBinding
import com.example.pharmacyapp.databinding.MedicineListItemBinding
import com.example.pharmacyapp.model.Medicine

class MedicineAdapter(private val clickListener: MedicineListener) :
    ListAdapter<Medicine, MedicineAdapter.ViewHolder>(MedicineDiffCallback()) {

    class ViewHolder private constructor(
        private val binding: MedicineListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            result: Medicine?,
            clickListener: MedicineListener
        ) {
            binding.medicine = result
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MedicineListItemBinding.inflate(layoutInflater, parent, false)
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

class MedicineDiffCallback : DiffUtil.ItemCallback<Medicine>() {
    override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem == newItem
    }

}

class MedicineListener(val clickListener: (medicineID: Int) -> Unit) {
    fun onClick(medicine: Medicine) = clickListener(medicine.id)
}
