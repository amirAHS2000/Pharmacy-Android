package com.example.pharmacyapp.ui.fragment.prepare

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class PrepareViewModel(application: Application) : AndroidViewModel(application)