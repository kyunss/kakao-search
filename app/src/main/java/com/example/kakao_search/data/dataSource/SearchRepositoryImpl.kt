package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.repository.SearchRepository
import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import javax.inject.Inject


internal class SearchRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
) : SearchRepository {

    override fun fetchBlogSearch(query: String, page: Int): Either<Failure, Search> {
        return remoteSource.fetchBlogSearch(
            query = query,
            page = page,
        )
    }

    override fun fetchCafeSearch(query: String, page: Int): Either<Failure, Search> {
        return remoteSource.fetchCafeSearch(
            query = query,
            page = page,
        )
    }

    override fun saveSearchResult(search: Search): Either<Failure, UseCase.None> {
        return localSource.saveSearchDocument(search)
    }

}