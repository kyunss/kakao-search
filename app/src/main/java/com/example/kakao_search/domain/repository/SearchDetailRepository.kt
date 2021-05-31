package com.example.kakao_search.domain.repository

import com.example.kakao_search.domain.detail.SearchDetail
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either


internal interface SearchDetailRepository {
    fun fetchSearchDetail(index: Int): Either<Failure, SearchDetail>
}