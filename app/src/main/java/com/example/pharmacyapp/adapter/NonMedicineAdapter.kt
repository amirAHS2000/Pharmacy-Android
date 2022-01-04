package com.example.pharmacyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.databinding.NonMedicineListItemBinding
import com.example.pharmacyapp.model.NonMedicine

class NonMedicineAdapter(private val clickListener: NonMedicineCLickListener) :
    ListAdapter<NonMedicine, NonMedicineAdapter.ViewHolder>(NonMedicineDiffCallBack()) {
    class ViewHolder private constructor(
        private val binding: NonMedicineListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            nonMedicine: NonMedicine?,
            clickListener: NonMedicineCLickListener
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

class NonMedicineDiffCallBack : DiffUtil.ItemCallback<NonMedicine>() {
    override fun areItemsTheSame(oldItem: NonMedicine, newItem: NonMedicine): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NonMedicine, newItem: NonMedicine): Boolean {
        return oldItem == newItem
    }

}

class NonMedicineCLickListener(val clickListener: (nonMedicineId: Int) -> Unit) {
    fun onclick(nonMedicine: NonMedicine) = clickListener(nonMedicine.id)
}
