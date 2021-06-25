/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * This base class is used for RecyclerView Adapter.
 * by extending this class will have common function for binding
 * the view. For extend this class we need four parameter such as :
 * @param B  for view  data binding
 * @param H for Recyclerview ViewHolder, the params a bounded parameters type in java
 * or Constraint type parameter in kotlin.
 * @param M for the model  or data
 * @param List for set the list of data
 * all parameters is required and can't set a null.
 * if  the set the null maybe NullPointerException will haunt you
 * the generic parameters will be given or attached an instance of
 * the corresponding parameter type according to  what we want,
 * then the parameter type will automatically become the base type in the class*/
abstract class BaseAdapter<B: ViewDataBinding, H: RecyclerView.ViewHolder> :
        RecyclerView.Adapter<H>() {
        /**
         * This function is used for set the view layout*/
    @LayoutRes
    protected abstract fun getResLayoutId(): Int

    /**
     * this function is used set the  RecyclerView viewHolder*/
    protected abstract fun getViewHolder(binding: B): H

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        return getViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getResLayoutId(),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: H, position: Int) {}
 }