package com.example.kakao_search.data.network

import com.example.kakao_search.data.entity.BlogEntity
import com.example.kakao_search.data.entity.CafeEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


internal interface KakaoApiService {
    companion object {
        private const val HEADERS = "Content-ResultType: application/json"
    }

    @Headers(HEADERS)
    @GET("/v2/search/cafe")
    fun fetchCafeSearch(@Query("query") query: String): Call<CafeEntity>

    @Headers(HEADERS)
    @GET("/v2/search/blog")
    fun fetchBlogSearch(@Query("query") query: String): Call<BlogEntity>

}