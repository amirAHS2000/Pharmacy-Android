package com.example.pharmacyapp.util.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.pharmacyapp.model.Photo

class ImageViewPagerDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}