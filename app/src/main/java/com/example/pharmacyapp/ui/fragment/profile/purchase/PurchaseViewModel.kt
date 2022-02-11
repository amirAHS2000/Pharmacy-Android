package com.example.pharmacyapp.ui.fragment.profile.purchase

import android.app.Application
import androidx.lifecycle.*
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.data.UserDataStore
import com.example.pharmacyapp.model.category.GetMedicinesInCategoryResponse
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _productPurchaseResponse =
        MutableLiveData<NetworkResult<GetMedicinesInCategoryResponse>>()
    val productPurchaseResponse: LiveData<NetworkResult<GetMedicinesInCategoryResponse>>
        get() = _productPurchaseResponse

    fun getProduct() = viewModelScope.launch {
        getProductSafeCall()
    }

    private lateinit var userDataStore: UserDataStore

    fun getToken() = viewModelScope.launch {
        userDataStore = repository.readUserData()
    }

    private suspend fun getProductSafeCall() {
        _productPurchaseResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response = repository.getAllMedicineInPresc(
                    "Bearer " + userDataStore.userToken,
                    1
                )
                _productPurchaseResponse.value = response.handle()
            } catch (e: Exception) {
                _productPurchaseResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _productPurchaseResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }
}