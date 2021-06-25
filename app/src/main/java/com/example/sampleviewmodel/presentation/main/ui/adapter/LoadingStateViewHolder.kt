/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 19/06/21 20.10 PM
 * Last modified 19/06/21 20.10 PM by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleviewmodel.R
import com.example.sampleviewmodel.databinding.ItemLoadStateBinding
/**
 * @ViewHolder class representation to view for handle layout in the RecyclerView*/
class LoadingStateViewHolder (
   private val binding: ItemLoadStateBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(binding.root) {
    init {
        /**
         * When the retry button is clicked it will generate
         * a callback, which will trigger the button to perform or display
         * the event.*/
        binding.retryBtn.setOnClickListener { retry.invoke()}
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryBtn.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(
            parent: ViewGroup,
            retry: () -> Unit
        ): LoadingStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state, parent, false)
            val binding = ItemLoadStateBinding.bind(view)
            return  LoadingStateViewHolder(binding, retry)
        }
    }
}