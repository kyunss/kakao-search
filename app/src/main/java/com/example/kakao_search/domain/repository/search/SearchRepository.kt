package com.example.kakao_search.domain.repository.search

import com.example.kakao_search.domain.search.GetSearch
import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either


internal interface SearchRepository {
    fun fetchSearchResult(
        query: String,
        sort: String,
        page: Int,
        size: Int = 25,
        filter: GetSearch.Params.SearchFilter = GetSearch.Params.SearchFilter.All
    ): Either<Failure, List<Search>>
}