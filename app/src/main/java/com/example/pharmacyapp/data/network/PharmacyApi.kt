package com.example.pharmacyapp.data.network

import com.example.pharmacyapp.model.*
import com.example.pharmacyapp.model.category.Category
import com.example.pharmacyapp.model.category.GetAllCategoryResponse
import com.example.pharmacyapp.model.category.GetMedicinesInCategoryResponse
import com.example.pharmacyapp.model.medicine.GetAllMedicinesResponse
import com.example.pharmacyapp.model.medicine.GetMedicineResponse
import com.example.pharmacyapp.model.user.UserInformationResponse
import retrofit2.Response
import retrofit2.http.*

interface PharmacyApi {

    //------------------------------------sign-up and login (prepare)--------------------------------//
    //sign-up and login (prepare)
    @POST("api/login/patient")
    suspend fun login(
        @Query("phone") phone: String,
        @Query("password") password: String,
    ): Response<LoginResponse>

    @POST("api/register")
    suspend fun signUp(
        @Query("ref_id") ref_id: Int,
        @Query("phone") phone: String,
        @Query("nat_num") nationalNumber: String,
        @Query("password") password: String,
        @Query("type") type: String,
    ): Response<LoginResponse>

    @POST("api/user/findPatient")
    suspend fun getUserInfoByPhone(
        @Query("phone") phone: String,
        @Query("nat_num") nationalNumber: String
    ): Response<UserResponse>

    @POST("api/register/patient")
    suspend fun createPatient(
        @Query("first_name") firstName: String,
        @Query("last_name") lastName: String,
        @Query("nat_num") nationalNumber: String,
        @Query("phone") phone: String,
        @Query("ins_num") insuranceNumber: String?,
        @Query("ins_id") insuranceId: Int?,
    ): Response<CreatePatientResponse>

    @POST("api/user/reset")
    suspend fun resetPassword(
        @Query("id") id: Int,
        @Query("password") password: String,
    ): Response<UserResponse>
    //------------------------------------sign-up and login (prepare)--------------------------------//

    //------------------------------------medicines and categories-----------------------------------//
    //get all categories
    @GET("api/categories")
    suspend fun getAllCategories(
        @Header("Authorization") token: String
    ): Response<GetAllCategoryResponse>

    //get all medicines
    @GET("api/meds")
    suspend fun getAllMedicines(
        @Header("Authorization") token: String
    ) : Response<GetAllMedicinesResponse>

    //get medicines in special category
    @GET("api/categories/meds/{id}")
    suspend fun getMedsInCategory(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<GetMedicinesInCategoryResponse>

    //get all information of special medicine
    @GET("api/meds/allInfo/{id}")
    suspend fun getAllInfoOfMed(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<GetMedicineResponse>

    //get all top sellers med
    @GET("api/meds/topSell/nd")
    suspend fun getMedsTopSeller(
        @Header("Authorization") token: String
    ): Response<GetMedicinesInCategoryResponse>

    //get all top sellers non med
    @GET("api/meds/topSell/dnd")
    suspend fun getNonMedTopSellers(
        @Header("Authorization") token: String
    ): Response<GetMedicinesInCategoryResponse>

    //search medicine by name
    @POST("api/meds/search")
    suspend fun searchMedicine(
        @Header("Authorization") token: String,
        @Query("name") medicineName: String
    ): Response<GetMedicinesInCategoryResponse>
    //------------------------------------medicines and categories-----------------------------------//

    //------------------------------------profile and user information-------------------------------//
    //get user information (profile)
    @GET("api/user")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): Response<UserInformationResponse>

    //------------------------------------profile and user information-------------------------------//

}