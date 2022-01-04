package com.example.pharmacyapp.ui.fragment.store

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.Category
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.NonMedicine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    val category = listOf<Category>(
        Category(id = 1, name = "c1", imageUri = ""),
        Category(id = 2, name = "c2", imageUri = ""),
        Category(id = 3, name = "c3", imageUri = ""),
        Category(id = 4, name = "c4", imageUri = ""),
        Category(id = 5, name = "c5", imageUri = ""),
        Category(id = 6, name = "c6", imageUri = ""),
        Category(id = 7, name = "c7", imageUri = ""),
        Category(id = 8, name = "c8", imageUri = ""),
        Category(id = 9, name = "c9", imageUri = "")
    )

    val topSellers = listOf<Medicine>(
        Medicine(id = 1, name = "t1", imageUri = ""),
        Medicine(id = 2, name = "t2", imageUri = ""),
        Medicine(id = 3, name = "t3", imageUri = ""),
        Medicine(id = 4, name = "t4", imageUri = ""),
        Medicine(id = 5, name = "t5", imageUri = ""),
        Medicine(id = 6, name = "t6", imageUri = ""),
        Medicine(id = 7, name = "t7", imageUri = ""),
        Medicine(id = 8, name = "t8", imageUri = ""),
        Medicine(id = 9, name = "t9", imageUri = "")
    )

    val nonMedicine = listOf<NonMedicine>(
        NonMedicine(id = 1, name = "n1", imageUri = ""),
        NonMedicine(id = 2, name = "n2", imageUri = ""),
        NonMedicine(id = 3, name = "n3", imageUri = ""),
        NonMedicine(id = 4, name = "n4", imageUri = ""),
        NonMedicine(id = 5, name = "n5", imageUri = ""),
        NonMedicine(id = 6, name = "n6", imageUri = ""),
        NonMedicine(id = 7, name = "n7", imageUri = ""),
        NonMedicine(id = 8, name = "n8", imageUri = ""),
        NonMedicine(id = 9, name = "n9", imageUri = "")
    )
}