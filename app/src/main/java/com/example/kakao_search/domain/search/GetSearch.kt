package com.example.kakao_search.domain.search

import com.example.kakao_search.domain.repository.search.SearchRepository
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import com.example.kakao_search.functional.getOrElse
import javax.inject.Inject


internal class GetSearch @Inject constructor(
    private val searchRepository: SearchRepository,
): UseCase<Search, GetSearch.Params>() {

    override suspend fun execute(params: Params): Either<Failure, Search> {
        return when (params.filter) {
            Filter.All -> {
                val blogResult = searchRepository.fetchBlogSearch(
                    query = params.query,
                    sort = params.sort,
                    page = params.page
                )

                val cafeResult = searchRepository.fetchCafeSearch(
                    query = params.query,
                    sort = params.sort,
                    page = params.page
                )

                return if (blogResult.isRight && cafeResult.isRight) {
                    //FixMe
                    val blogSearch = blogResult.getOrElse(Failure.ServerError) as Search
                    val cafeSearch = cafeResult.getOrElse(Failure.ServerError) as Search

                    Either.Right(blogSearch + cafeSearch)
                } else {
                    Either.Left(Failure.ServerError)
                }
            }

            Filter.Type.Blog -> {
                searchRepository.fetchBlogSearch(
                    query = params.query,
                    sort = params.sort,
                    page = params.page
                )
            }

            Filter.Type.Cafe -> {
                searchRepository.fetchCafeSearch(
                    query = params.query,
                    sort = params.sort,
                    page = params.page
                )
            }
        }
    }

    internal data class Params(
        val query: String,
        val sort: String,
        val page: Int,
        val size: Int,
        val filter: Filter
    )

}