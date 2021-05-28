package com.example.kakao_search.data.network

import com.example.kakao_search.domain.repository.search.SearchRepository
import com.example.kakao_search.domain.search.GetSearch
import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import javax.inject.Inject


internal class SearchRepositoryImpl @Inject constructor() : SearchRepository {
    override fun fetchSearchResult(query: String, sort: String, page: Int, size: Int, filter: GetSearch.Params.SearchFilter): Either<Failure, List<Search>> {
        return when (filter) {
            GetSearch.Params.SearchFilter.All -> Either.Left(Failure.NoNetworkConnection)
            GetSearch.Params.SearchFilter.Blog -> Either.Left(Failure.NoNetworkConnection)
            GetSearch.Params.SearchFilter.Cafe -> Either.Left(Failure.NoNetworkConnection)
        }
    }

}