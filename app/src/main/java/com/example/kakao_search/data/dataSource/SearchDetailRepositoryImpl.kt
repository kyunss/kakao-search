package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.detail.SearchDetail
import com.example.kakao_search.domain.repository.SearchDetailRepository
import com.example.kakao_search.domain.repository.SearchRepository
import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import javax.inject.Inject


internal class SearchDetailRepositoryImpl @Inject constructor(
    private val localSource: LocalSource
) : SearchDetailRepository {

    override fun fetchSearchDetail(index: Int): Either<Failure, SearchDetail> {
        return localSource.fetchSearchDocument(index)
    }

}