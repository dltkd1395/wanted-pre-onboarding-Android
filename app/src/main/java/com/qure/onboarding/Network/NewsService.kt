package com.qure.onboarding.Network

import com.qure.onboarding.AppContents
import com.qure.onboarding.model.NewsBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")

    fun getInfo(
        @Query("country") country: String = AppContents.COUNTRY,
        @Query("apiKey") apiKey : String = AppContents.API_KEY
    ) : Call<NewsBody>

    @GET("top-headlines")
    fun getCategory(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey : String
    ) : Call<NewsBody>

}