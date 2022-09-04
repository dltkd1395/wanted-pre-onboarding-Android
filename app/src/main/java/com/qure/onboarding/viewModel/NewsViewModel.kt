package com.qure.onboarding.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qure.onboarding.Room.Saved
import com.qure.onboarding.model.Category
import com.qure.onboarding.model.News
import java.text.SimpleDateFormat
import java.time.Instant

class NewsViewModel : ViewModel() {

    val news = MutableLiveData<News>()
    val category = MutableLiveData<List<Category>>()
    val item_category = MutableLiveData<Category>()
    val news_list = MutableLiveData<MutableList<Saved>>()


    fun sendNewsInfo(info: News) {
        news.value = info
    }

    fun getCategory(item : List<Category>){

        category.value = item
    }

    fun getCategoryName(item : Category){

        item_category.value = item

    }




}