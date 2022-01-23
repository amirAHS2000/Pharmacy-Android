package com.example.pharmacyapp.util.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.pharmacyapp.model.medicine.FullInfoMedModel

class MedicineLargeDiffCallBack : DiffUtil.ItemCallback<FullInfoMedModel>() {
    override fun areItemsTheSame(oldItem: FullInfoMedModel, newItem: FullInfoMedModel): Boolean {
        return oldItem.med.id == newItem.med.id
    }

    override fun areContentsTheSame(oldItem: FullInfoMedModel, newItem: FullInfoMedModel): Boolean {
        return oldItem == newItem
    }

}