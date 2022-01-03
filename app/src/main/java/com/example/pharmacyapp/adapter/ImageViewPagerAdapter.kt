package com.example.pharmacyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pharmacyapp.databinding.ImageViewpagerLayoutBinding
import com.example.pharmacyapp.model.Photo

class ImageViewPagerAdapter() :
    ListAdapter<Photo, ImageViewPagerAdapter.ViewHolder>(
        ImageViewPagerDiffCallback()
    ) {

    class ViewHolder private constructor(private val binding: ImageViewpagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Photo
        ) {
            binding.photo = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater =
                    LayoutInflater.from(parent.context)
                val binding = ImageViewpagerLayoutBinding.inflate(layoutInflater, parent, false)

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

class ImageViewPagerDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

}
