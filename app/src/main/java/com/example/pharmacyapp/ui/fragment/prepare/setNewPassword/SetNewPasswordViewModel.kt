package com.example.pharmacyapp.ui.fragment.prepare.setNewPassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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

    fun setUser(user: User) {
        _user.value = user
    }

    fun onNavigateToLogin() {
        _navigateToLogin.value = true
    }

    fun onNavigateToLoginDone() {
        _navigateToLogin.value = null
    }

    fun resetPassword(password: String){
        // TODO reset password
    }
}