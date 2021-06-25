/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 23.44 PM
 * Last modified 13/06/21 23.44 PM by Nurholis
 */
package com.example.sampleviewmodel.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sampleviewmodel.data.model.ArticlesItem
import com.example.sampleviewmodel.data.response.Response
import com.example.sampleviewmodel.network.ApiServiceDataSource
import com.example.sampleviewmodel.network.NewsPagingSource
import com.example.sampleviewmodel.presentation.main.external.utils.constant.I
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
  private val  apiServiceDataSource: ApiServiceDataSource
): NewsRepository {
    override suspend fun getNews(): Flow<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = I.PAGE_SIZE,
                maxSize =  I.PAGE_SIZE + (I.PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = {NewsPagingSource(apiServiceDataSource)}
        ).flow
    }

    override suspend fun getFilter(
        date: Date
    ): Flow<Response> {
        return apiServiceDataSource.getFilter(
            date = date
        )
    }
}