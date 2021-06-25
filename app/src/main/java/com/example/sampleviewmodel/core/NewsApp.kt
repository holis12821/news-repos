/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  16.28 AM by Nurholis
 */
package com.example.sampleviewmodel.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *@HiltAndroidApp this annotation to initialize
 * Dagger hilt in android apps*/

@HiltAndroidApp
class NewsApp: Application() {
}