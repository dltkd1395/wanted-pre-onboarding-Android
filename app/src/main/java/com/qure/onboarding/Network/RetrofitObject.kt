package com.qure.onboarding.Network

import com.qure.onboarding.AppContents
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(AppContents.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getNewsService(): NewsService {
        return getRetrofit().create(NewsService::class.java)
    }

}