package com.example.kakao_search.domain.search

import com.example.kakao_search.domain.repository.search.SearchRepository
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import javax.inject.Inject


internal class GetBlog @Inject constructor(
    private val searchRepository: SearchRepository
): UseCase<Search, GetBlog.Params>() {

    override suspend fun execute(params: Params): Either<Failure, Search> {
        return searchRepository.fetchBlogSearch(
            query = params.query,
            sort = params.sort,
            page = params.page
        )
    }

    internal data class Params(
        val query: String,
        val sort: String,
        val page: Int,
        val size: Int
    )

}