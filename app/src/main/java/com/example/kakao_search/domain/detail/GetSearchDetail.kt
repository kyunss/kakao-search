package com.example.kakao_search.domain.detail

import com.example.kakao_search.domain.repository.SearchDetailRepository
import com.example.kakao_search.domain.repository.SearchRepository
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import com.example.kakao_search.functional.getOrElse
import javax.inject.Inject


internal class GetSearchDetail @Inject constructor(
    private val searchDetailRepository: SearchDetailRepository,
) : UseCase<SearchDetail, GetSearchDetail.Params>() {

    override suspend fun execute(params: Params): Either<Failure, SearchDetail> {
        return searchDetailRepository.fetchSearchDetail(index = params.index)
    }

    internal data class Params(
        val index: Int
    )

}