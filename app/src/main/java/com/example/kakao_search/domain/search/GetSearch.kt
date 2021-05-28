package com.example.kakao_search.domain.search

import com.example.kakao_search.domain.repository.search.SearchRepository
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import javax.inject.Inject


internal class GetSearch @Inject constructor(
    private val searchRepository: SearchRepository
): UseCase<List<Search>, GetSearch.Params>() {

    // FixMe: SearchRepository 에서 filter 까지 입력받아 메서드를 하나로 통합할까 고민
    override suspend fun execute(params: Params): Either<Failure, List<Search>> {
        return searchRepository.fetchSearchResult(
            query = params.query,
            sort = params.sort,
            page = params.page
        )
    }

    internal data class Params(
        val query: String,
        val sort: String,
        val page: Int,
        val size: Int,
        val filter: SearchFilter
    ) {
        sealed class SearchFilter {
            object All: SearchFilter()
            object Blog: SearchFilter()
            object Cafe: SearchFilter()
        }
    }

}