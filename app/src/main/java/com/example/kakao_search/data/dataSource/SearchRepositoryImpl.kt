package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.repository.search.SearchRepository
import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import javax.inject.Inject


internal class SearchRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
) : SearchRepository {

    override fun fetchBlogSearch(query: String, sort: String, page: Int, size: Int): Either<Failure, Search> {
        return remoteSource.fetchBlogSearch(
            query = query,
            sort = sort,
            page = page,
            size = size,
        )
    }

    override fun fetchCafeSearch(query: String, sort: String, page: Int, size: Int): Either<Failure, Search> {
        return remoteSource.fetchCafeSearch(
            query = query,
            sort = sort,
            page = page,
            size = size,
        )
    }
}