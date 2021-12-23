package com.example.pharmacyapp.ui.fragment.prepare.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pharmacyapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private fun onNavigateToMain() {
        _navigateToMain.value = true
    }

    fun onNavigateToMainDone() {
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
        // create a user and navigate if it was successful
        onNavigateToMain()
    }
}