package com.qure.domain.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("urlToImage")
    var urlToImage: String,
    @SerializedName("publishedAt")
    var publishedAt : String,
    @SerializedName("content")
    var content: String
)

