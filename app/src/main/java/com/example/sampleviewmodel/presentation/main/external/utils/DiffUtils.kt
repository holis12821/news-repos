/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 19/06/21 23.44 PM
 * Last modified 19/06/21 23.44 PM by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.external.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.sampleviewmodel.data.model.ArticlesItem

object DiffUtils {
    val COMPARATOR = object : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(
            oldItem: ArticlesItem,
            newItem: ArticlesItem
        ): Boolean = oldItem.source?.id == newItem.source?.id

        override fun areContentsTheSame(
            oldItem: ArticlesItem,
            newItem: ArticlesItem
        ): Boolean = oldItem == newItem

    }
}