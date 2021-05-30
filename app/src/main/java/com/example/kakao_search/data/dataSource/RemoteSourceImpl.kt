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

    override fun fetchBlogSearch(query: String, sort: String, page: Int, size: Int): Either<Failure, Search> {
        return request(
            call = kakaoApiService.fetchBlogSearch(query),
            transform = { blogEntity -> blogEntity.toSearch() },
            BlogEntity.empty()
        )
    }

    override fun fetchCafeSearch(query: String, sort: String, page: Int, size: Int): Either<Failure, Search> {
        return request(
            call = kakaoApiService.fetchCafeSearch(query),
            transform = { cafeEntity -> cafeEntity.toSearch() },
            CafeEntity.empty()
        )
    }

//    fun fetchAll(query: String, sort: String, page: Int, size: Int): Either<Failure, Search> {
//        val blogResult = request(
//            call = kakaoApiService.fetchBlogSearch(query),
//            transform = { blogEntity -> blogEntity.toSearch() },
//            BlogEntity.empty()
//        )
//
//        val cafeResult = request(
//            call = kakaoApiService.fetchCafeSearch(query),
//            transform = { cafeEntity -> cafeEntity.toSearch() },
//            CafeEntity.empty()
//        )
//    }

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

//    private fun <T, R> request2(
//        requestType: RequestType<T, R>
//    ): Either<Failure, R> {
//        return try {
//            val response = requestType.call.execute()
//
//            when (response.isSuccessful) {
//                true -> Either.Right(requestType.transform((response.body() ?: requestType.default)))
//                false -> Either.Left(Failure.ServerError)
//            }
//        } catch (exception: Throwable) {
//            Either.Left(Failure.ServerError)
//        }
//    }

//    private fun <T, R> multiRequest(
//        multiRequest: List<RequestType<T, R>>
//    ): Either<Failure, R> {
//        val mutableList = mutableListOf<R>()
//        return try {
//            multiRequest.forEach { requestType ->
//                val response = requestType.call.execute()
//
//                if (response.isSuccessful) {
//                    mutableList.add(requestType.transform(response.body() ?: requestType.default))
//                }
//            }
//        } catch (exception: Throwable) {
//            Either.Left(Failure.ServerError)
//        }
//    }
//
//    data class RequestType<T, R>(
//        val call: Call<T>,
//        val transform: (T) -> R,
//        val default: T
//    )

}