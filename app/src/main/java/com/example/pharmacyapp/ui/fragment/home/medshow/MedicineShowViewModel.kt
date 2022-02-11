package com.example.pharmacyapp.ui.fragment.home.medshow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.data.UserDataStore
import com.example.pharmacyapp.model.medicine.GetAllMedicinesResponse
import com.example.pharmacyapp.model.medicine.GetMedicineResponse
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineShowViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _medicineResponse = MutableLiveData<NetworkResult<GetAllMedicinesResponse>>()
    val medicineResponse: LiveData<NetworkResult<GetAllMedicinesResponse>>
        get() = _medicineResponse

    fun getMedicines() = viewModelScope.launch {
        getMedicinesSafeCall()
    }

    private lateinit var userDataStore: UserDataStore

    fun getToken() = viewModelScope.launch {
        userDataStore = repository.readUserData()
    }

    private suspend fun getMedicinesSafeCall() {
        val token = "Bearer 3|eEYvFhVhellWYPrK7mIkVT6kp20QOdY2c3iCcOEP"
        _medicineResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response = repository.getAllMedicines("Bearer " + userDataStore.userToken)
                _medicineResponse.value = response.handle()
            } catch (e: Exception) {
                _medicineResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _medicineResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }
}