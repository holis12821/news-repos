package com.example.sampleviewmodel.presentation.main.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class NewsLoadStateAdapter (
  private val retry: () -> Unit
): LoadStateAdapter<LoadingStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
       holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder = LoadingStateViewHolder.create(parent, retry)
}