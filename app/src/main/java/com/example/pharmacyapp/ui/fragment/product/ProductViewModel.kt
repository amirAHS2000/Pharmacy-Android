package com.example.pharmacyapp.ui.fragment.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.Photo
import com.example.pharmacyapp.model.medicine.GetMedicineResponse
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    private val repository: Repository,
    private val state: SavedStateHandle
) : AndroidViewModel(application) {

    private var medicineId : Int? = null

    init {
       medicineId = state.get<Int>("productId")
    }

    private val _medicineResponse = MutableLiveData<NetworkResult<GetMedicineResponse>>()
    val medicineResponse: LiveData<NetworkResult<GetMedicineResponse>>
        get() = _medicineResponse

    fun getMedicine() = viewModelScope.launch {
        getMedicineSafeCall(medicineId!!)
    }

    private suspend fun getMedicineSafeCall(medicineId: Int) {
        val token = "Bearer 3|eEYvFhVhellWYPrK7mIkVT6kp20QOdY2c3iCcOEP"
        _medicineResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response = repository.getAllInfoOfMed(token, medicineId)
                _medicineResponse.value = response.handle()
            } catch (e: Exception) {
                _medicineResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _medicineResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }
}