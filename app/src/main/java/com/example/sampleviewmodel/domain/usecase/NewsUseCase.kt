/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 23.44 PM
 * Last modified 13/06/21  23.44 PM by Nurholis
 */
package com.example.sampleviewmodel.domain.usecase

import androidx.paging.PagingData
import com.example.sampleviewmodel.data.model.ArticlesItem
import com.example.sampleviewmodel.data.response.Response
import com.example.sampleviewmodel.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class NewsUseCase @Inject constructor( private val newsRepository: NewsRepository) {
    suspend operator fun invoke (): Flow<PagingData<ArticlesItem>> {
        return newsRepository.getNews()
    }
}