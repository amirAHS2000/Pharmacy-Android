package com.example.pharmacyapp.ui.fragment.prepare

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PrepareViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrepareViewModel::class.java)) {
            return PrepareViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}