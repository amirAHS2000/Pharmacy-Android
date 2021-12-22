package com.example.pharmacyapp.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmacyapp.adapter.CategoryAdapter
import com.example.pharmacyapp.adapter.MedicineAdapter
import com.example.pharmacyapp.model.Category
import com.example.pharmacyapp.model.Medicine

@BindingAdapter("listDataCategory")
fun bindRecyclerViewCategory(recyclerView: RecyclerView, data: List<Category>?) {
    val adapter = recyclerView.adapter as CategoryAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataMedicine")
fun bindRecyclerViewMedicine(recyclerView: RecyclerView, data: List<Medicine>?) {
    val adapter = recyclerView.adapter as MedicineAdapter
    adapter.submitList(data)
}