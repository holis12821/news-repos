/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 23.45 PM
 * Last modified 19/06/21 19.45 PM by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleviewmodel.R
import com.example.sampleviewmodel.data.model.ArticlesItem
import com.example.sampleviewmodel.databinding.ItemNewsAdapterBinding
import com.example.sampleviewmodel.presentation.main.external.utils.DiffUtils
import com.example.sampleviewmodel.presentation.main.external.utils.extention.gone
import com.example.sampleviewmodel.presentation.main.external.utils.extention.visible

/**
 * Adapters representation to handle data from list in the RecyclerView
 * */
class NewsAdapter: PagingDataAdapter<ArticlesItem, NewsAdapter.NewsHolderList>(DiffUtils.COMPARATOR) {

    inner class NewsHolderList(
            private val binding: ItemNewsAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem?) {
            binding.data = data
            if (data?.urlToImage == null && data?.url == null) {
                binding.itemProfileImg.gone()
                binding.itemImage.gone()
                binding.imgLikes.gone()
            } else {
                Glide.with(itemView)
                        .load(data.urlToImage)
                        .into(binding.itemProfileImg)

                Glide.with(itemView)
                        .load(data.urlToImage)
                        .into(binding.itemImage)
            }
        }
    }

    override fun onBindViewHolder(holder: NewsHolderList, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolderList {
        return NewsHolderList(
                DataBindingUtil.inflate(
                        LayoutInflater.from(
                                parent.context),
                        R.layout.item_news_adapter,
                        parent, false
                )
        )
    }
}