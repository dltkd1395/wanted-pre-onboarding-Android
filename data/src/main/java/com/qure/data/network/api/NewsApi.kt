package com.qure.data.network.api


import com.qure.data.network.model.Article
import com.qure.data.network.model.NewsResponse
import com.qure.domain.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
     suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("category") category: String? = null,
        @Query("page") page : Int = 1
    ) : NewsResponse

}