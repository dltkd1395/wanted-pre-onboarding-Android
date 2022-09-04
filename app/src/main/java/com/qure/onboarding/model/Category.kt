package com.qure.onboarding.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(

    val img : Int? = 0,
    val name : String? = ""

) : Parcelable
