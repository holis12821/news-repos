/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.sampleviewmodel.presentation.main.ui.adapter.NewsAdapter

/**
 * This base fragment class is used for fragment that using view
 * and data binding. by extending this class will have common
 * function for binding the view.
 * for extended this class we need two parameters :
 * @param B for view data binding
 * */

abstract class BaseFragment<B : ViewDataBinding>: Fragment() {

    /**
     * this variable used for binding the view*/
    protected lateinit var binding: B

    /**
     * this function is used for set the view layout
     * add annotation @LayoutRes*/
    @LayoutRes
    protected abstract fun getResLayoutId(): Int

    /**
     * @onViewCreated() : this function is used for set
     * the action when the view was created
     * */
    protected abstract fun onViewCreated()
    /**
     * the Adapter will be called by subclass.
     * The lazy expression will be called when needed*/
    protected val adapter by lazy {NewsAdapter()}


    /**
     * this function is used for init all function when fragment is
     * created. there're have function for binding the view
     * with data binding. this
     * @onCreateView() is a method when layout on fragment is attach
     * or if not attachment in the layout then it won't run the
     * @onViewCreated() function.
     * @lifeCycleOwner is an interface that shows that this class
     * has a lifecycle such as an activity or a fragment.
     * so the activity or fragment implements a @LifecycleOwner,
     * then the activity and fragment have properties that are owned
     * by the interface so they have a lifecycle.
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<B>(inflater,
        getResLayoutId(), container, false)
            .apply {
                lifecycleOwner = this@BaseFragment
            }
        onViewCreated()
        return binding.root
    }

    protected fun handleError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error
        errorState?.let {
            Toast.makeText(context, "${it.error}", Toast.LENGTH_LONG).show()
        }
    }

}