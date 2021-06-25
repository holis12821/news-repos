/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  16.27 PM by Nurholis
 */
package com.example.sampleviewmodel.di

import com.example.sampleviewmodel.presentation.main.external.utils.constant.I
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Module : this annotation in dagger hilt indicates that so that the class
 * or object is not initialized in various classes or places,
 * it uses this annotation.
 *
 * @InstallIn : Hilt modules are standard Dagger modules that have an additional @InstallIn
 * annotation that determines which hilt component(s) to install the module into.
 * Using @InstallIn the module is installed in the Hilt Component by annotating the module with
 * the @InstallIn annotation. this annotation is required on all Dagger modules when using Hilt, but
 * this check may be done on a training basis.
 * when the hilt components are generated, the modules annotated with @InstallIn will be installed into
 * the corresponding component or SubComponent via
 * @Component#module or
 * @SubComponent#module respectively.
 * Just like in Dagger, installing a module into a component allows that binding to be accessed as a dependency
 * of other bindings in that component or any child component(s) below it in the component hierarchy.
 * They can also be accessed from the corresponding
 * @AndroidEntryPoint classes. Being installed in a component also allows that binding to be scoped to that component
 *
 * @note : If the module does not have the @InstallIn annotation, the module will not be part of the component
 * and may cause compilation errors.
 *
 * Specify which Hilt Component to install the module with the appropriate component type(s) to
 * the @InstallIn annotation.
 * for example, to install a module so that anything in the application can use it, use
 * @SingletonComponent
 *
 * @see "Documentation : https://dagger.dev/hilt/modules" this documentation is officially by Dagger hilt
 * */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(I.NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(I.NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(I.NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}