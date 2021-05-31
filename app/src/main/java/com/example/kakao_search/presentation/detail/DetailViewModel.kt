package com.example.kakao_search.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kakao_search.domain.detail.GetSearchDetail
import com.example.kakao_search.domain.detail.SearchDetail
import com.example.kakao_search.domain.search.Filter
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import com.example.kakao_search.presentation.core.BaseViewModel
import com.example.kakao_search.support.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val getSearchDetail: GetSearchDetail
) : BaseViewModel() {

    val searchDetailView: LiveData<SearchDetailView>
        get() = _searchDetailView
    private val _searchDetailView = SingleLiveEvent<SearchDetailView>()

    fun loadSearchDetail(index: Int) {
        getSearchDetail(
            params = GetSearchDetail.Params(index = index),
            scope = viewModelScope,
            onResult = { result: Either<Failure, SearchDetail> ->
                result.fold(::handleFailure, ::handleSearchDetail)
            }
        )
    }

    private fun handleSearchDetail(searchDetail: SearchDetail) {
        _searchDetailView.value = SearchDetailView(
            type = when (searchDetail.type) {
                is Filter.Type.Blog -> "Blog"
                is Filter.Type.Cafe -> "Cafe"
            },
            thumbnail = searchDetail.thumbnail,
            name = searchDetail.name,
            title = searchDetail.title,
            contents = searchDetail.contents,
            dateTime = "",
            url = searchDetail.url
        )
    }

}
