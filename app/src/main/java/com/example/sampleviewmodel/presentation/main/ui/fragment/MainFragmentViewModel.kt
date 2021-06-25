/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 25/06/21 21.16 by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.sampleviewmodel.data.model.ArticlesItem
import com.example.sampleviewmodel.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
): ViewModel() {

    init {
        getNews()
    }
    //state
    private var _state = MutableStateFlow<MainFragmentViewState>(MainFragmentViewState.Init)
    val state: StateFlow<MainFragmentViewState> get() = _state

    @InternalCoroutinesApi
    fun getNews() {
        viewModelScope.launch {
            newsUseCase.invoke()
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    e.message?.let {
                        showMessage(it)
                    }
                }.collect {
                    hideLoading()
                   showNews(it)
                }
        }
    }

    private fun showLoading() {
        _state.value = MainFragmentViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _state.value = MainFragmentViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String) {
        _state.value = MainFragmentViewState.ShowError(message)
    }

    private fun showNews(pagingData: PagingData<ArticlesItem>) {
        _state.value = MainFragmentViewState.ShowData(pagingData)
    }

}