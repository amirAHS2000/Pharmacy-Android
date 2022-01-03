package com.example.pharmacyapp.ui.fragment.prepare.forgetpassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.User
import com.example.pharmacyapp.model.UserResponse
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    private var _navigateToSetNewPassword = MutableLiveData<User?>()
    val navigateToSetNewPassword: LiveData<User?>
        get() = _navigateToSetNewPassword

    private var _user = MutableLiveData<NetworkResult<UserResponse>?>()
    val user: LiveData<NetworkResult<UserResponse>?>
        get() = _user

    fun onNavigateToSetNewPassword(user: User) {
        _navigateToSetNewPassword.value = user
    }

    fun onNavigateToSetNewPasswordDone() {
        _navigateToSetNewPassword.value = null
        _user.value = null
    }

    fun onFindUser(phone: String, nationalNumber: String) {
        viewModelScope.launch {
            findUserSafeCall(phone, nationalNumber)
        }
    }

    private suspend fun findUserSafeCall(phone: String, nationalNumber: String) {
        _user.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                _user.value = repository.findUserByPhone(phone, nationalNumber).handle()
            } catch (e: Exception) {
                _user.value = NetworkResult.Error("There Was Something Wrong")
            }
        } else {
            _user.value = NetworkResult.Error("No Internet Connection")
        }
    }
}