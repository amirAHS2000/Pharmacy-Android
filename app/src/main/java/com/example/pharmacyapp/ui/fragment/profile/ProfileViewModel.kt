package com.example.pharmacyapp.ui.fragment.profile

import android.app.Application
import androidx.lifecycle.*
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.data.UserDataStore
import com.example.pharmacyapp.model.user.UserInformationResponse
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    private val _userInformationResponse = MutableLiveData<NetworkResult<UserInformationResponse>>()
    val userInformationResponse: LiveData<NetworkResult<UserInformationResponse>>
        get() = _userInformationResponse

    fun getUserInformation() = viewModelScope.launch {
        getUserInformationSafeCall()
    }

    private lateinit var userDataStore: UserDataStore

    fun getToken() = viewModelScope.launch {
        userDataStore = repository.readUserData()
    }

    private suspend fun getUserInformationSafeCall() {
//        val token = "Bearer 255|UyQ4I9RJB7DCYtOhYCQjXRbNrbtQqIbui0BJAlQj"
        _userInformationResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                val response = repository.getUserInformation("Bearer " + userDataStore.userToken)
                _userInformationResponse.value = response.handle()
            } catch (e: Exception) {
                _userInformationResponse.value = NetworkResult.Error("Medicine Not Found.")
            }
        } else {
            _userInformationResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }
}