package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.detail.SearchDetail
import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either


internal interface LocalSource {
    fun fetchSearchDocument(index: Int): Either<Failure, SearchDetail>
    fun saveSearchDocument(search: Search): Either<Failure, UseCase.None>
}