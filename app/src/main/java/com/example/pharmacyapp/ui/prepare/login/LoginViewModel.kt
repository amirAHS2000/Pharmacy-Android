package com.example.pharmacyapp.ui.prepare.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pharmacyapp.network.PharmacyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var _navigateToMain = MutableLiveData<Boolean?>()
    val navigateToMain: LiveData<Boolean?>
        get() = _navigateToMain

    private var _navigateToForgotPassword = MutableLiveData<Boolean?>()
    val navigateToForgotPassword: LiveData<Boolean?>
        get() = _navigateToForgotPassword

    private var _navigateToSignUp = MutableLiveData<Boolean?>()
    val navigateToSignUp: LiveData<Boolean?>
        get() = _navigateToSignUp

    fun onNavigateToMain() {
        _navigateToMain.value = true
    }

    fun onNavigateToMainDone() {
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
        viewModelScope.launch(Dispatchers.IO) {
//            lateinit var res : NetworkUserContainer
            try {
                PharmacyNetwork.pharmacy.login(phone, password)
            } catch (e : Exception){
               Log.e("retrofit", e.stackTraceToString() )
            }
//            Log.d("pharmacy-login", res.toString())
        }
    }
}