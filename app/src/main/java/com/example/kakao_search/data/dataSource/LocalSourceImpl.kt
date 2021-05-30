package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.detail.SearchDetail
import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import retrofit2.Call
import javax.inject.Inject


internal class LocalSourceImpl @Inject constructor(
    private val searchResultCache: SearchResultCache
) : LocalSource {

    override fun fetchSearchDocument(index: Int): Either<Failure, SearchDetail> {
        return if (index < 0 || index > searchResultCache.searchResult.documents.size) {
            Either.Left(Failure.NotFoundInCache)
        } else {
            val document = searchResultCache.searchResult.documents[index]

            Either.Right(
                SearchDetail(
                    type = document.type,
                    thumbnail = document.thumbnail,
                    name = document.name,
                    title = document.title,
                    contents = document.contents,
                    dateTime = document.dateTime,
                    url = document.url
                )
            )
        }
    }

    override fun saveSearchDocument(search: Search): Either<Failure, UseCase.None> {
        searchResultCache.searchResult = searchResultCache.searchResult + search

        return Either.Right(UseCase.None())
    }

}