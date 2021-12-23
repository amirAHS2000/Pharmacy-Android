package com.example.pharmacyapp.ui.fragment.prepare.forgetpassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pharmacyapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    private var _navigateToSetNewPassword = MutableLiveData<Boolean?>()
    val navigateToSetNewPassword: LiveData<Boolean?>
        get() = _navigateToSetNewPassword

    fun onNavigateToSetNewPassword() {
        _navigateToSetNewPassword.value = true
    }

    fun onNavigateToSetNewPasswordDone() {
        _navigateToSetNewPassword.value = null
    }

    fun onFindUser(phone: String): Boolean {
        return repository.findUserByPhone(phone)
    }
}