/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 25/06/21  21.17 by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.ui.fragment

import androidx.paging.PagingData
import com.example.sampleviewmodel.data.model.ArticlesItem

/**
 * sealed class used to representation a finite class hierarchy.
 * @Sealed support developers to maintain data types of predefined types.
 * To create closed class, we need to use the command "sealed" as a modifier for the class
 * */

sealed class MainFragmentViewState {
    object Init: MainFragmentViewState()
    data class Progress(val isLoading: Boolean): MainFragmentViewState()
    data class ShowData(val data: PagingData<ArticlesItem>? = null): MainFragmentViewState()
    data class ShowError(val error: String): MainFragmentViewState()
}
