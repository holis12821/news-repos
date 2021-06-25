/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.external.utils.extention

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * this extension function can use to view
 * with call to function and than use the simple easy
 * and reusable code*/

fun View.gone() {
    visibility = View.GONE
}

fun  View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun RecyclerView.setUpLinearLayoutManager(@RecyclerView.Orientation orientation: Int) {
    layoutManager = LinearLayoutManager(context, orientation, false)
}





