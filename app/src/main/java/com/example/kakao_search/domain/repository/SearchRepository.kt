package com.example.kakao_search.domain.repository

import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either


internal interface SearchRepository {
    fun fetchBlogSearch(query: String, page: Int): Either<Failure, Search>

    fun fetchCafeSearch(query: String, page: Int, ): Either<Failure, Search>

    fun saveSearchResult(search: Search): Either<Failure, UseCase.None>
}