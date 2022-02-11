package com.example.pharmacyapp.data

import com.example.pharmacyapp.model.LoginResponse
import com.example.pharmacyapp.model.UserResponse
import com.example.pharmacyapp.model.category.GetAllCategoryResponse
import com.example.pharmacyapp.model.category.GetMedicinesInCategoryResponse
import com.example.pharmacyapp.model.medicine.GetAllMedicinesResponse
import com.example.pharmacyapp.model.medicine.GetMedicineResponse
import com.example.pharmacyapp.model.prescription.CreatePrescriptionResponse
import com.example.pharmacyapp.model.prescription.PrescriptionContentResponse
import com.example.pharmacyapp.model.user.UserInformationResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreRepository
) {

    //----------------------------------------PREPARE------------------------------------------------//
    suspend fun findUserByPhone(phone: String, nationalNumber: String): Response<UserResponse> {
        return remoteDataSource.findUserByPhone(phone, nationalNumber)
    }

    suspend fun login(phone: String, password: String): Response<LoginResponse> {
        return remoteDataSource.login(phone, password)
    }

    suspend fun signUp(
        firstName: String,
        lastName: String,
        nationalNumber: String,
        phone: String,
        password: String,
        insuranceNumber: String? = null,
        insuranceId: Int? = null,
    ): Response<LoginResponse> {
        val patient = remoteDataSource.createPatient(
            firstName,
            lastName,
            nationalNumber,
            phone,
            insuranceNumber,
            insuranceId
        )
        return when {
            !patient.isSuccessful -> Response.error(patient.code(), patient.errorBody()!!)

            patient.body()?.status == false -> Response.success(
                LoginResponse(
                    status = false,
                    message = patient.body()!!.message
                )
            )

            else -> {
                val refId = patient.body()?.result?.first()?.patient?.id!!
                remoteDataSource.signUp(refId, nationalNumber, phone, password, "patient")
            }
        }

    }

    suspend fun createPrescription(
        token: String,
        doctorName: String,
        patientId: Int
    ): Response<CreatePrescriptionResponse> {
        return remoteDataSource.createPrescription(token, doctorName, patientId)
    }

    suspend fun resetPassword(id: Int, password: String): Response<UserResponse> {
        return remoteDataSource.resetPassword(id, password)
    }

    suspend fun saveUserLocally(id: Int, token: String): Boolean {
        return dataStore.saveUserData(id, token)
    }

    suspend fun readUserData(): UserDataStore {
        return dataStore.readUserData()
    }

    //----------------------------------------PREPARE------------------------------------------------//


    //----------------------------------------MEDICINES AND CATEGORY---------------------------------//
    suspend fun getCategories(token: String): Response<GetAllCategoryResponse> {
        return remoteDataSource.getCategories(token)
    }

    suspend fun getAllMedicines(token: String): Response<GetAllMedicinesResponse> {
        return remoteDataSource.getAllMedicines(token)
    }

    suspend fun getMedsInCategory(
        token: String,
        categoryId: Int
    ): Response<GetMedicinesInCategoryResponse> {
        return remoteDataSource.getMedsInCategory(token, categoryId)
    }

    suspend fun getAllInfoOfMed(token: String, medicineId: Int): Response<GetMedicineResponse> {
        return remoteDataSource.getAllInfoOfMed(token, medicineId)
    }

    suspend fun getMedTopSellers(token: String): Response<GetMedicinesInCategoryResponse> {
        return remoteDataSource.getMedTopSeller(token)
    }

    suspend fun getNonMedTopSellers(token: String): Response<GetMedicinesInCategoryResponse> {
        return remoteDataSource.getNonMedTopSellers(token)
    }

    suspend fun searchMedicine(
        token: String,
        medicineName: String
    ): Response<GetMedicinesInCategoryResponse> {
        return remoteDataSource.searchMedicine(token, medicineName)
    }
    //----------------------------------------MEDICINES AND CATEGORY---------------------------------//


    //----------------------------------------PROFILE (USER INFORMATION)---------------------------------------//

    suspend fun getUserInformation(
        token: String
    ): Response<UserInformationResponse> {
        return remoteDataSource.getUserInfo(token)
    }

    suspend fun addPrescriptionContent(
        token: String,
        prescId: Int,
        medId: Int,
        insBuy: Boolean
    ): Response<PrescriptionContentResponse> {
        return remoteDataSource.addPrescriptionContent(token, prescId, medId, insBuy)
    }

    suspend fun getAllMedicineInPresc(
        token: String,
        prescId: Int
    ): Response<GetMedicinesInCategoryResponse> {
        return remoteDataSource.getAllMedicineInPresc(token, prescId)
    }

    //----------------------------------------PROFILE (USER INFORMATION)---------------------------------------//
}