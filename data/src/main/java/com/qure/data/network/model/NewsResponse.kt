package com.qure.data.network.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val status: String,
    val totalResults: Int?,
    val articles: List<Article>?,
    val code: String?,
    val message: String?
)

data class Article(
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