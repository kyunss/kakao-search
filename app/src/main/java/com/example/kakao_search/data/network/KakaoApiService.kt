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
        private const val PAGING_SIZE = 10
    }

    @Headers(HEADERS)
    @GET("/v2/search/blog")
    fun fetchBlogSearch(@Query("query") query: String, @Query("page")page: Int, @Query("size")size: Int = PAGING_SIZE): Call<BlogEntity>

    @Headers(HEADERS)
    @GET("/v2/search/cafe")
    fun fetchCafeSearch(@Query("query") query: String, @Query("page")page: Int, @Query("size")size: Int = PAGING_SIZE): Call<CafeEntity>

}