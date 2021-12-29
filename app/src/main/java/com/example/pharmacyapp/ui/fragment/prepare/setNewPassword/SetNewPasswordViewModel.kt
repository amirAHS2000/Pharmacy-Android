package com.example.pharmacyapp.ui.fragment.prepare.setNewPassword

import android.app.Application
import android.util.Log
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
import kotlin.math.log

@HiltViewModel
class SetNewPasswordViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private var _navigateToLogin = MutableLiveData<Boolean?>()
    val navigateToLogin: LiveData<Boolean?>
        get() = _navigateToLogin

    private var _resetPasswordResult = MutableLiveData<NetworkResult<UserResponse>>()
    val resetPasswordResult: LiveData<NetworkResult<UserResponse>>
        get() = _resetPasswordResult

    fun setUser(user: User) {
        _user.value = user
    }

    fun onNavigateToLogin() {
        _navigateToLogin.value = true
    }

    fun onNavigateToLoginDone() {
        _navigateToLogin.value = null
    }

    fun onResetPassword(password: String){
        val id = user.value?.id
        if (id != null) {
            viewModelScope.launch {
                resetPasswordSafeCall(id, password)
            }
        }
    }

    private suspend fun resetPasswordSafeCall(id: Int, password: String){
        _resetPasswordResult.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                _resetPasswordResult.value = repository.resetPassword(id, password).handle()
            } catch (e: Exception) {
                _resetPasswordResult.value = NetworkResult.Error("There Was Something Wrong")
                Log.e("server request error", e.stackTraceToString())
                e.printStackTrace()
            }
        } else {
            _resetPasswordResult.value = NetworkResult.Error("No Internet Connection")
        }
    }
}