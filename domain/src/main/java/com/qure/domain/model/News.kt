package com.qure.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "bookmark_table")
@Parcelize
data class News(
    @PrimaryKey
    val title: String = "",
    val author: String? = "",
    val description: String?="",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = "",
    val isSaved: Boolean = false
): Parcelable
