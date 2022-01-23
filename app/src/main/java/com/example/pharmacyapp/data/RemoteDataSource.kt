package com.example.pharmacyapp.data

import com.example.pharmacyapp.data.network.PharmacyApi
import com.example.pharmacyapp.model.*
import com.example.pharmacyapp.model.category.Category
import com.example.pharmacyapp.model.category.GetAllCategoryResponse
import com.example.pharmacyapp.model.category.GetMedicinesInCategoryResponse
import com.example.pharmacyapp.model.medicine.GetAllMedicinesResponse
import com.example.pharmacyapp.model.medicine.GetMedicineResponse
import com.example.pharmacyapp.model.user.UserInformationResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val pharmacyApi: PharmacyApi,
) {

    //----------------------------------------PREPARE------------------------------------------------//
    suspend fun login(phoneNumber: String, password: String): Response<LoginResponse> {
        return pharmacyApi.login(phoneNumber, password)
    }

    suspend fun signUp(
        ref_id: Int,
        nationalNumber: String,
        phone: String,
        password: String,
        type: String,
    ): Response<LoginResponse> {
        return pharmacyApi.signUp(ref_id, phone, nationalNumber, password, type)
    }

    suspend fun createPatient(
        firstName: String,
        lastName: String,
        nationalNumber: String,
        phone: String,
        insuranceNumber: String?,
        insuranceId: Int?,
    ): Response<CreatePatientResponse> {
        return pharmacyApi.createPatient(
            firstName,
            lastName,
            nationalNumber,
            phone,
            insuranceNumber,
            insuranceId
        )
    }

    suspend fun findUserByPhone(phone: String, nationalNumber: String): Response<UserResponse> {
        return pharmacyApi.getUserInfoByPhone(phone, nationalNumber)
    }

    suspend fun resetPassword(id: Int, password: String): Response<UserResponse> {
        return pharmacyApi.resetPassword(id, password)
    }

    //----------------------------------------PREPARE------------------------------------------------//

    //----------------------------------------MEDICINES AND CATEGORY---------------------------------//
    //get all categories
    suspend fun getCategories(token: String): Response<GetAllCategoryResponse> {
        return pharmacyApi.getAllCategories(token)
    }

    //get all medicines
    suspend fun getAllMedicines(token: String): Response<GetAllMedicinesResponse> {
        return pharmacyApi.getAllMedicines(token)
    }

    //get medicines in special category
    suspend fun getMedsInCategory(
        token: String,
        categoryId: Int
    ): Response<GetMedicinesInCategoryResponse> {
        return pharmacyApi.getMedsInCategory(token, categoryId)
    }

    //get all information of special medicine
    suspend fun getAllInfoOfMed(token: String, medicineId: Int): Response<GetMedicineResponse> {
        return pharmacyApi.getAllInfoOfMed(token, medicineId)
    }

    //get all top sellers med
    suspend fun getMedTopSeller(token: String): Response<GetMedicinesInCategoryResponse> {
        return pharmacyApi.getMedsTopSeller(token)
    }

    //get all top sellers non med
    suspend fun getNonMedTopSellers(token: String): Response<GetMedicinesInCategoryResponse> {
        return pharmacyApi.getNonMedTopSellers(token)
    }

    //search medicine by name
    suspend fun searchMedicine(
        token: String,
        medicineName: String
    ): Response<GetMedicinesInCategoryResponse> {
        return pharmacyApi.searchMedicine(token, medicineName)
    }

    //----------------------------------------MEDICINES AND CATEGORY---------------------------------//

    //----------------------------------------PROFILE (USER INFORMATION)---------------------------------------//

    //get user information
    suspend fun getUserInfo(
        token: String
    ): Response<UserInformationResponse> {
        return pharmacyApi.getUserInfo(token)
    }

    //----------------------------------------PROFILE (USER INFORMATION)---------------------------------------//
}