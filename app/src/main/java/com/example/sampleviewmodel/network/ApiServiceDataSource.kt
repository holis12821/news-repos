/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.network

import com.example.sampleviewmodel.data.response.Response
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

/**
 * @Query this query annotation will hold a value which is the value added to the parameter as a value
 * for sorting data etc. and will create request or request get.
 * @QueryMap while the annotation query map we can directly filter the data at once based on the key and value.
 * and if the data you want to sort is more than 1, desc or asc.*/

/**
 * @Path used to change paths or manipulate endpoints which are marked with "post/{postNumber}"
 * and the name must match the pathname and the path will be added to the API.
 * The value of the path annotation must be the same as the value in the get annotation at the endpoint.
 * */

interface ApiServiceDataSource {
    @GET("everything")
    suspend fun getNews(
        @Query("q") keyword: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response

    @GET("everything")
    suspend fun getFilter (
        @Query("from") date: Date,
    ): Flow<Response>
}