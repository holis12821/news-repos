/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  23.44 PM by Nurholis
 */
package com.example.sampleviewmodel.di

import com.example.sampleviewmodel.domain.repository.NewsRepository
import com.example.sampleviewmodel.domain.repository.NewsRepositoryImpl
import com.example.sampleviewmodel.network.ApiServiceDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideServiceNewsDataSource(retrofit: Retrofit): ApiServiceDataSource
    = retrofit.create(ApiServiceDataSource::class.java)
}