package com.example.pharmacyapp.model.medicine

data class GetAllMedicinesResponse(
    val message: List<String> = listOf(),
    val result: List<FullInfoMedModel> = listOf(),
    val status: Boolean
)
