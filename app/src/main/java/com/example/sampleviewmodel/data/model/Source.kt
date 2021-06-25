/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.data.model

import com.google.gson.annotations.SerializedName

data class Source(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Any? = null
)

