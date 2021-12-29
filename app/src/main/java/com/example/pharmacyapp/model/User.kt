package com.example.pharmacyapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val created_at: String?,
    val id: Int,
    val ref_id: Int,
    val phone: String?,
    val updated_at: String?,
) : Parcelable