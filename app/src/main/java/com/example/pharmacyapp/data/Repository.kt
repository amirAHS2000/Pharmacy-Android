package com.example.pharmacyapp.data

import com.example.pharmacyapp.network.models.LoginResponse
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun findUserByPhone(phone: String) : Boolean{
//        remoteDataSource.findUserByPhone()
        return true // TODO implement this
    }

    suspend fun login(phone: String, password: String): LoginResponse? {
        return remoteDataSource.login(phone, password).body()
    }

    val remote = remoteDataSource // TODO remove this


}