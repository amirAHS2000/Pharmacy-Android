package com.example.pharmacyapp.ui.fragment.prepare.signup

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) :
    AndroidViewModel(application) {

    private var _navigateToMain = MutableLiveData<Boolean?>()
    val navigateToMain: LiveData<Boolean?>
        get() = _navigateToMain

    private var _navigateToLogin = MutableLiveData<Boolean?>()
    val navigateToLogin: LiveData<Boolean?>
        get() = _navigateToLogin

    private var _signUpResponse = MutableLiveData<NetworkResult<LoginResponse>>()
    val signUpResponse: LiveData<NetworkResult<LoginResponse>>
        get() = _signUpResponse

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

    fun onNavigateToLogin() {
        _navigateToLogin.value = true
    }

    fun onNavigateToLoginDone() {
        _navigateToLogin.value = null
    }

    fun onSignUp(
        firstname: String,
        lastname: String,
        nationalNumber: String,
        phone: String,
        password: String,
    ) {
        viewModelScope.launch {
            _signUpResponse.value =
                repository.signUp(firstname, lastname, nationalNumber, phone, password).handle()
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