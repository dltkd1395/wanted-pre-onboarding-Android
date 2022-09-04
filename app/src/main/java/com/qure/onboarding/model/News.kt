package com.qure.onboarding.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class NewsBody(

    val status: String,
    val totalResults: Int,
    val articles: List<News>
) : Parcelable

@Parcelize
data class News(

    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = ""
    ) : Parcelable