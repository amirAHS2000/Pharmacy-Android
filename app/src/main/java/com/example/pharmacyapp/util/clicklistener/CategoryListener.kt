package com.example.pharmacyapp.util.clicklistener

import com.example.pharmacyapp.model.Category

class CategoryListener(val clickListener: (categoryID: Int) -> Unit) {
    fun onClick(category: Category) = clickListener(category.id)
}
