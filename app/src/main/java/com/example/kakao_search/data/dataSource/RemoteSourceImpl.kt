package com.example.kakao_search.data.dataSource

import com.example.kakao_search.data.entity.BlogEntity
import com.example.kakao_search.data.entity.CafeEntity
import com.example.kakao_search.data.entity.toSearch
import com.example.kakao_search.data.network.KakaoApiService
import com.example.kakao_search.domain.search.Search
import com.example.kakao_search.domain.useCase.UseCase
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import retrofit2.Call
import javax.inject.Inject


internal class RemoteSourceImpl @Inject constructor(
    private val kakaoApiService: KakaoApiService
): RemoteSource {

    override fun fetchBlogSearch(query: String, page: Int): Either<Failure, Search> {
        return request(
            call = kakaoApiService.fetchBlogSearch(query = query, page = page),
            transform = { blogEntity -> blogEntity.toSearch() },
            BlogEntity.empty()
        )
    }

    override fun fetchCafeSearch(query: String, page: Int): Either<Failure, Search> {
        return request(
            call = kakaoApiService.fetchCafeSearch(query = query, page = page),
            transform = { cafeEntity -> cafeEntity.toSearch() },
            CafeEntity.empty()
        )
    }

    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()

            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }

}