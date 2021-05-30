package com.example.kakao_search.domain.search

import com.example.kakao_search.domain.repository.SearchRepository
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import javax.inject.Inject


internal class SaveSearch @Inject constructor(
    private val searchRepository: SearchRepository,
) : UseCase<UseCase.None, SaveSearch.Params>() {

    override suspend fun execute(params: Params): Either<Failure, None> {
        return searchRepository.saveSearchResult(params.search)
    }

    internal data class Params(
        val search: Search
    )

}