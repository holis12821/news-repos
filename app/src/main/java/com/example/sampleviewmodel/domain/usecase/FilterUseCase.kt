package com.example.sampleviewmodel.domain.usecase

import com.example.sampleviewmodel.data.response.Response
import com.example.sampleviewmodel.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class FilterUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(
        keyword: String,
        date: Date
    ): Flow<Response> {
        return newsRepository.getFilter(
            keyWord = keyword,
            date = date
        )
    }
}