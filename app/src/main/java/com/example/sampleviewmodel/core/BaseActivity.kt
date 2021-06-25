/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * this base activity class is used for activity that using view
 * and data binding by extending this class will have common function for
 * binding the view.
 * for extend this class we need two parameter
 * @param B for view data binding
 * and both parameter is required and can't set to null.
 * this parameter is generic constraint type parameter
 * which is used to restrict what generic types to attach.
 * */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    /**
     * this variable is use for binding the view*/
    private lateinit var binding: B

    /*this function is used for set the view layout
    * */
    @LayoutRes
    protected abstract fun getResLayoutId(): Int

    /**
     * this function used for set the action
     * when the activity was created*/
    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    /**
     * this function is used for init all function some think like that
     * when activity is read to layout and next to call function
     * @onActivityCreated().
     * there're have function for binding the view with data binding
     * @onCreate() this function will be executed  when the layout
     * of the activity has been attach to the activity.
     * @lifeCycleOwner is an interface that shows that this class
     * has a lifecycle such as an activity or a fragment.
     * so the activity or fragment implements a @LifecycleOwner,
     * then the activity and fragment have properties that are owned
     * by the interface so they have a lifecycle.*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<B>(this, getResLayoutId())
            .apply {
                lifecycleOwner = this@BaseActivity
            }
    }

}