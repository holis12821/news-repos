/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.ui.fragment

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleviewmodel.R
import com.example.sampleviewmodel.core.BaseFragment
import com.example.sampleviewmodel.data.model.ArticlesItem
import com.example.sampleviewmodel.databinding.MainFragmentBinding
import com.example.sampleviewmodel.presentation.main.external.utils.extention.gone
import com.example.sampleviewmodel.presentation.main.external.utils.extention.setUpLinearLayoutManager
import com.example.sampleviewmodel.presentation.main.external.utils.extention.visible
import com.example.sampleviewmodel.presentation.main.ui.adapter.NewsLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding>() {
    @InternalCoroutinesApi
    private val viewModel by viewModels<MainFragmentViewModel>()
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun getResLayoutId(): Int  = R.layout.main_fragment

    @InternalCoroutinesApi
    override fun onViewCreated() {
        //code is here
        initAdapter()
        onNewsObserver()
    }

    private fun initAdapter() {
        binding.apply {
            rvNews.setHasFixedSize(true)
            rvNews.setUpLinearLayoutManager(LinearLayoutManager.VERTICAL)
           rvNews.adapter = adapter.withLoadStateFooter(
               footer = NewsLoadStateAdapter {adapter.retry()}
           )
            adapter.addLoadStateListener { loadState ->
                val refreshState = loadState.source.refresh
                binding.rvNews.isVisible = refreshState is LoadState.NotLoading
                binding.progressBar.isVisible = refreshState is LoadState.Loading
                binding.retryBtn.isVisible = refreshState is LoadState.Error
                handleError(loadState)
            }
            retryBtn.setOnClickListener { adapter.retry() }
        }
    }

    @InternalCoroutinesApi
    private fun onNewsObserver() {
        lifecycleScope.launch {
            viewModel.state
                .onEach {
                    handleFragmentViewState(it)
                }.launchIn(this)
        }
    }

    private fun handleFragmentViewState(state: MainFragmentViewState) {
        when (state) {
            is MainFragmentViewState.Init -> Unit
            is MainFragmentViewState.Progress -> onProgress(state.isLoading)
            is MainFragmentViewState.ShowError -> onShowMessage(state.error)
            is MainFragmentViewState.ShowData -> state.data?.let {
                onShowNewsList(it) }
        }
    }

    private fun onProgress(loading: Boolean) {
        if (loading) binding.progressBar.visible() else binding.progressBar.gone()
    }

    private fun onShowMessage(message: String) {
        Toast.makeText(context, message , Toast.LENGTH_SHORT).show()
    }

    private fun onShowNewsList(pagingData: PagingData<ArticlesItem>) {
        adapter.submitData(this.lifecycle, pagingData)
    }
}