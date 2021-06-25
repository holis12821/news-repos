/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 10.17 AM
 * Last modified 13/06/21  10.18 by Nurholis
 */
package com.example.sampleviewmodel.data.model

import com.google.gson.annotations.SerializedName

/**
 * data class ArticleItem representation the  data per item for the Json object
 * which the parsing data of the jSON
 * */

data class ArticlesItem(
    /**
     * @SerializeName : this annotation tell the compiler that if the property
     * name is different from the JSON key name, it will indicate that this is
     * the key for JSON and the value is assigned to the property.
     * */
    @field:SerializedName("publishedAt")
    var publishedAt: String? = null,

    @field:SerializedName("author")
    var author: String? = null,

    @field:SerializedName("urlToImage")
    var urlToImage: String? = null,

    @field:SerializedName("description")
    var description: String? = null,

    @field:SerializedName("source")
    var source: Source? = null,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("url")
    var url: String? = null,

    @field:SerializedName("content")
    var content: String? = null
)
