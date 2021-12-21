package com.example.pharmacyapp.ui.fragment.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pharmacyapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val repository: Repository,
    application: Application
) :
    AndroidViewModel(application) {

}