/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 23.43 PM
 * Last modified 13/06/21  23.43 PM by Nurholis
 */
package com.example.sampleviewmodel.domain.repository

import androidx.paging.PagingData
import com.example.sampleviewmodel.data.model.ArticlesItem
import com.example.sampleviewmodel.data.response.Response
import kotlinx.coroutines.flow.Flow
import java.util.*

interface NewsRepository {
    suspend fun getNews(): Flow<PagingData<ArticlesItem>>
    suspend fun getFilter(
        date: Date
    ): Flow<Response>
}