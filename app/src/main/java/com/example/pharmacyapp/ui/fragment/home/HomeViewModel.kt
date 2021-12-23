package com.example.pharmacyapp.ui.fragment.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    var medsResponse: MutableLiveData<NetworkResult<List<Medicine>>> = MutableLiveData()

    fun getMedicine(medName: String) = viewModelScope.launch {
        getMedicineSafeCall(medName)
    }

    private suspend fun getMedicineSafeCall(medName: String) {
        medsResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response = repository.remote.getMedicine(medName)
                medsResponse.value = response.handle()
            } catch (e: Exception) {
                medsResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            medsResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

//    private fun handleMedicineResponse(response: Response<List<Medicine>>): NetworkResult<List<Medicine>>? {
//        when {
//            response.message().toString().contains("timeout") -> {
//                return NetworkResult.Error("Timeout.")
//            }
//            response.code() == 402 -> {
//                return NetworkResult.Error("API Key Limited.")
//            }
//            // TODO: 12/19/2021 must change this part (according to Medicine model)
//            response.body()!!.isNullOrEmpty() -> {
//                return NetworkResult.Error("Medicine Not Found.")
//            }
//            response.isSuccessful -> {
//                return NetworkResult.Success(response.body()!!)
//            }
//            else -> {
//                return NetworkResult.Error(response.message())
//            }
//        }
//    }
//
//    private fun hasInternetConnection(): Boolean {
//        val connectivityManager = getApplication<Application>().getSystemService(
//            Context.CONNECTIVITY_SERVICE
//        ) as ConnectivityManager
//        val activeNetwork = connectivityManager.activeNetwork ?: return false
//        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//
//        return when {
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//            else -> false
//        }
//    }
}