/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sampleviewmodel.R
import com.example.sampleviewmodel.presentation.main.ui.fragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * They can also be accessed from the corresponding
 * @AndroidEntryPoint classes. Being installed in
 * a component also allows that binding to be
 * scoped to that component
 * */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}