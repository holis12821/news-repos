/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 19/06/21 23.44 PM
 * Last modified 19/06/21 23.44 PM by Nurholis
 */
package com.example.sampleviewmodel.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sampleviewmodel.data.model.ArticlesItem
import com.example.sampleviewmodel.presentation.main.external.utils.constant.I
import com.example.sampleviewmodel.presentation.main.external.utils.constant.K
import kotlinx.coroutines.delay
import okio.IOException
import retrofit2.HttpException
/**
 * PagingSource to handle data from network
 * Paging is Architecture Component by Google inc.
 * Paging handle many data from hundreds to millions of  data.
 * When we scroll the  data list down, the paging will set how much data we want
 * to load, this will be useful for reducing internet data quota from application users
 * when using paging we must use @DiffUtils on the adapter we create  which is an instance  of
 * @PagingDataAdapter*/
class NewsPagingSource (
  private val apiServiceDataSource: ApiServiceDataSource
 ): PagingSource<Int, ArticlesItem>()  {
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.minus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        return try {
            val page = params.key ?: I.STARTING_PAGE_INDEX
            val response = apiServiceDataSource.getNews(
                page = page,
                pageSize = params.loadSize,
                apiKey = K.API_KEY
            )
            val newsList = response.articles
            //delay before data is received
            delay(2000)
            LoadResult.Page(
                data = newsList,
                prevKey = if (page == I.STARTING_PAGE_INDEX) null else page -1,
                nextKey = if (newsList.isNullOrEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}