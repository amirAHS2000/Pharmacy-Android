package com.example.pharmacyapp.ui.fragment.prepare.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.LoginResponse
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.handle
import com.example.pharmacyapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    private var _navigateToMain = MutableLiveData<Boolean?>()
    val navigateToMain: LiveData<Boolean?>
        get() = _navigateToMain

    private var _navigateToForgotPassword = MutableLiveData<Boolean?>()
    val navigateToForgotPassword: LiveData<Boolean?>
        get() = _navigateToForgotPassword

    private var _navigateToSignUp = MutableLiveData<Boolean?>()
    val navigateToSignUp: LiveData<Boolean?>
        get() = _navigateToSignUp

    private var _loginResponse = MutableLiveData<NetworkResult<LoginResponse>>()
    val loginResponse: LiveData<NetworkResult<LoginResponse>>
        get() = _loginResponse

    private var _saveUserState = MutableLiveData<Boolean?>()
    val saveUserState: LiveData<Boolean?>
        get() = _saveUserState

    fun onNavigateToMain() {
        _navigateToMain.value = true
    }

    fun onNavigateToMainDone() {
        _saveUserState.value = null
        _navigateToMain.value = null
    }

    fun onNavigateToForgetPassword() {
        _navigateToForgotPassword.value = true
    }

    fun onNavigateToForgetPasswordDone() {
        _navigateToForgotPassword.value = null
    }

    fun onNavigateToSignUp() {
        _navigateToSignUp.value = true
    }

    fun onNavigateToSignUpDone() {
        _navigateToSignUp.value = null
    }

    fun onLogin(phone: String, password: String) {
        viewModelScope.launch {
            loginSafeCall(phone, password)
        }
    }

    private suspend fun loginSafeCall(phone: String, password: String) {
        _loginResponse.value = NetworkResult.Loading()
        if (hasInternetConnection(getApplication())) {
            try {
                _loginResponse.value = repository.login(phone, password).handle()
            } catch (e: Exception) {
                _loginResponse.value = NetworkResult.Error("There Was Something Wrong")
            }
        } else {
            _loginResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    fun onSaveUser(loginResponse: LoginResponse) {
        val userAndToken = loginResponse.result.first()
        val id = userAndToken.user.id
        val token = userAndToken.token
        viewModelScope.launch {
            try {
                saveUserSafeCall(id, token)
            } catch (e: IOException) {
                Log.e("DataStore", e.stackTraceToString())
            } catch (e: Exception) {
                Log.e("DataStore", e.stackTraceToString())
            }
        }
    }

    private suspend fun saveUserSafeCall(id: Int, token: String) {
        _saveUserState.value = repository.saveUserLocally(id, token)
    }
}