package com.example.kakao_search.data.network

import com.example.kakao_search.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


internal class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        builder.addHeader("Authorization", "Bearer ${BuildConfig.KAKAO_DEBUG_API_KEY}")

        return chain.proceed(builder.build())
    }

}