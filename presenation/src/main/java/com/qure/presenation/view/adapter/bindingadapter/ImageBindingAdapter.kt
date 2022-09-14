package com.qure.presenation.view.adapter.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @BindingAdapter("newsImage")
    @JvmStatic
    fun userImage(imageView: ImageView, uri : String?){

        Glide.with(imageView.context).load(uri).into(imageView)
    }


}