package com.qure.onboarding.Room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "saved_table")
@Parcelize
class Saved(

    @PrimaryKey var title : String,
    var author: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String

) : Parcelable