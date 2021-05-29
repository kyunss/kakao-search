package com.example.kakao_search.domain.repository.search

import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either


internal interface SearchRepository {
    fun fetchBlogSearch(
        query: String,
        sort: String,
        page: Int,
        size: Int = 25,
    ): Either<Failure, Search>

    fun fetchCafeSearch(
        query: String,
        sort: String,
        page: Int,
        size: Int = 25,
    ): Either<Failure, Search>
}