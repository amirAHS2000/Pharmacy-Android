package com.example.pharmacyapp.ui.fragment.store

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.data.UserDataStore
import com.example.pharmacyapp.model.category.Category
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.Photo
import com.example.pharmacyapp.model.category.GetAllCategoryResponse
import com.example.pharmacyapp.model.category.GetMedicinesInCategoryResponse
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {


    //-------------------------navigate to medicines fragment----------------------//
    private val _navigateToMedicine = MutableLiveData<Int?>()
    val navigateToMedicine: LiveData<Int?>
        get() = _navigateToMedicine

    fun onMedicineItemClicked(id: Int) {
        _navigateToMedicine.value = id
    }

    fun onMedicineNavigated() {
        _navigateToMedicine.value = null
    }

    //-----------------------navigate to non-medicines fragment--------------------//
    private val _navigateToNonMedicine = MutableLiveData<Int?>()
    val navigateToNonMedicine: LiveData<Int?>
        get() = _navigateToNonMedicine

    fun onNonMedicineClicked(id: Int) {
        _navigateToNonMedicine.value = id
    }

    fun onNonMedicineNavigated() {
        _navigateToNonMedicine.value = null
    }

    //---------------------navigate to product (detail) fragment-------------------//
    private val _navigateToProduct = MutableLiveData<Int?>()
    val navigateToProduct: LiveData<Int?>
        get() = _navigateToProduct

    fun onProductClicked(id: Int) {
        _navigateToProduct.value = id
    }

    fun onProductNavigated() {
        _navigateToProduct.value = null
    }

    //---------------------categories and medicines--------------------//
    private val _categoriesResponse = MutableLiveData<NetworkResult<GetAllCategoryResponse>>()
    val categories: LiveData<NetworkResult<GetAllCategoryResponse>>
        get() = _categoriesResponse

    private val _medicineTopSellersResponse =
        MutableLiveData<NetworkResult<GetMedicinesInCategoryResponse>>()
    val medicineTopSellers: LiveData<NetworkResult<GetMedicinesInCategoryResponse>>
        get() = _medicineTopSellersResponse

    private val _nonMedicineTopSellersResponse =
        MutableLiveData<NetworkResult<GetMedicinesInCategoryResponse>>()
    val nonMedicineTopSellers: LiveData<NetworkResult<GetMedicinesInCategoryResponse>>
        get() = _nonMedicineTopSellersResponse

    private val _searchResultResponse =
        MutableLiveData<NetworkResult<GetMedicinesInCategoryResponse>>()
    val searchResultResponse: LiveData<NetworkResult<GetMedicinesInCategoryResponse>>
        get() = _searchResultResponse

    private lateinit var userDataStore: UserDataStore

    fun getToken() = viewModelScope.launch {
        userDataStore = repository.readUserData()
    }

    fun getSearchResult(medicineName: String) = viewModelScope.launch {
        getSearchResultSafeCall(medicineName)
    }

    private suspend fun getSearchResultSafeCall(medicineName: String) {
//        val token = "Bearer 3|eEYvFhVhellWYPrK7mIkVT6kp20QOdY2c3iCcOEP"
        _searchResultResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response =
                    repository.searchMedicine("Bearer " + userDataStore.userToken, medicineName)
                _searchResultResponse.value = response.handle()
            } catch (e: Exception) {
                _searchResultResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _searchResultResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    fun getCategories() = viewModelScope.launch {
        getCategoriesSafeCall()
    }

    private suspend fun getCategoriesSafeCall() {
//        val token = "Bearer 3|eEYvFhVhellWYPrK7mIkVT6kp20QOdY2c3iCcOEP"
        _categoriesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response = repository.getCategories("Bearer " + userDataStore.userToken)
                _categoriesResponse.value = response.handle()
            } catch (e: Exception) {
                _categoriesResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _categoriesResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    fun getMedicineTopSellers() = viewModelScope.launch {
        getMedicineTopSellersSafeCall()
    }

    private suspend fun getMedicineTopSellersSafeCall() {
//        val token = "Bearer 3|eEYvFhVhellWYPrK7mIkVT6kp20QOdY2c3iCcOEP"
        _medicineTopSellersResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response = repository.getMedTopSellers("Bearer " + userDataStore.userToken)
                _medicineTopSellersResponse.value = response.handle()
            } catch (e: Exception) {
                _medicineTopSellersResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _medicineTopSellersResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    fun getNonMedicineTopSellers() = viewModelScope.launch {
        getNonMedicineTopSellersSafeCall()
    }

    private suspend fun getNonMedicineTopSellersSafeCall() {
//        val token = "Bearer 3|eEYvFhVhellWYPrK7mIkVT6kp20QOdY2c3iCcOEP"
        _nonMedicineTopSellersResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response = repository.getNonMedTopSellers("Bearer " + userDataStore.userToken)
                _nonMedicineTopSellersResponse.value = response.handle()
            } catch (e: Exception) {
                _nonMedicineTopSellersResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _nonMedicineTopSellersResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }
}