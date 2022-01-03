package com.example.pharmacyapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object GlideModule {
//
//    @Singleton
//    @Provides
//    fun provideGlide(@ActivityContext context: Context): RequestManager = Glide.with(context)
//}