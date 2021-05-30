package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either


internal interface RemoteSource {
    fun fetchBlogSearch(query: String, page: Int,): Either<Failure, Search>
    fun fetchCafeSearch(query: String, page: Int,): Either<Failure, Search>
}