package com.qure.presenation.widget

import com.qure.presenation.widget.Utils.API
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain)=
        chain.proceed(
            chain.request().newBuilder().apply {
                header("X-Api-Key", API)
            }.build()
        )
}