package com.example.pharmacyapp.util.clicklistener

import com.example.pharmacyapp.model.category.CategoriesResult

class CategoryListener(val clickListener: (categoryID: Int) -> Unit) {
    fun onClick(category: CategoriesResult) = clickListener(category.category.id)
}
