package com.qure.onboarding.BindingAdatper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.qure.onboarding.R

object ImageBindingAdapter {

    @BindingAdapter("newsImage")
    @JvmStatic
    fun userImage(imageView: ImageView, uri : String?){

        Glide.with(imageView.context).load(uri).into(imageView)
    }


}