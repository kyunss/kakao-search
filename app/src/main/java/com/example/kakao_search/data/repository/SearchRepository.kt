package com.example.kakao_search.data.repository

import com.example.kakao_search.data.entity.Cafe
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either


internal interface SearchRepository {
    fun fetchSearchResult(query: String): Either<Failure, List<Cafe>>
}