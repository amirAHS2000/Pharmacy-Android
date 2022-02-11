package com.example.pharmacyapp.ui.fragment.medicines

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.data.UserDataStore
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.Photo
import com.example.pharmacyapp.model.category.GetMedicinesInCategoryResponse
import com.example.pharmacyapp.model.category.MedicineInCategoryModel
import com.example.pharmacyapp.model.medicine.GetMedicineResponse
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val repository: Repository,
    application: Application,
    private val state: SavedStateHandle
) : AndroidViewModel(application) {

    private var categoryId: Int? = null

    init {
        categoryId = state.get<Int>("medCategoryId")
    }

    private val _medicineResponse = MutableLiveData<NetworkResult<GetMedicinesInCategoryResponse>>()
    val medicineResponse: LiveData<NetworkResult<GetMedicinesInCategoryResponse>>
        get() = _medicineResponse

    //--------------------------------navigate to product fragment ---------------------------//
    private val _navigateToProduct = MutableLiveData<Int?>()
    val navigateToProduct: LiveData<Int?>
        get() = _navigateToProduct

    fun onMedicineClicked(id: Int) {
        _navigateToProduct.value = id
    }

    fun onMedicineNavigated() {
        _navigateToProduct.value = null
    }

    fun getMedicine() = viewModelScope.launch {
        getMedicineSafeCall(categoryId!!)
    }

    private lateinit var userDataStore: UserDataStore

    fun getToken() = viewModelScope.launch {
        userDataStore = repository.readUserData()
    }

    // TODO: 1/19/2022 need to access to token
    private suspend fun getMedicineSafeCall(categoryId: Int) {
//        val token = "Bearer 3|eEYvFhVhellWYPrK7mIkVT6kp20QOdY2c3iCcOEP"
        _medicineResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response =
                    repository.getMedsInCategory("Bearer " + userDataStore.userToken, categoryId)
                _medicineResponse.value = response.handle()
            } catch (e: Exception) {
                _medicineResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _medicineResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }
}