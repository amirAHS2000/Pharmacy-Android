package com.example.pharmacyapp.data

import com.example.pharmacyapp.model.LoginResponse
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun findUserByPhone(phone: String) : Boolean{
//        remoteDataSource.findUserByPhone()
        return true // TODO implement this
    }

    suspend fun login(phone: String, password: String): Response<LoginResponse> {
        return remoteDataSource.login(phone, password)
    }

    val remote = remoteDataSource // TODO remove this


}