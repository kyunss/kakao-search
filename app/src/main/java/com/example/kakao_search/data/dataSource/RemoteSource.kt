package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either


internal interface RemoteSource {
    fun fetchBlogSearch(query: String, sort: String, page: Int, size: Int): Either<Failure, List<Search>>
    fun fetchCafeSearch(query: String, sort: String, page: Int, size: Int): Either<Failure, List<Search>>
}