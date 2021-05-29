package com.example.kakao_search.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakao_search.R
import com.example.kakao_search.domain.search.*
import com.example.kakao_search.domain.search.GetBlog
import com.example.kakao_search.domain.search.GetCafe
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import com.example.kakao_search.presentation.search.list.SearchItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val getBlog: GetBlog,
    private val getCafe: GetCafe

) : ViewModel() {

    private var page = 0
    private val size = 25

    val searchResult: LiveData<List<SearchItem>>
        get() = _searchResult
    private val _searchResult = MutableLiveData<List<SearchItem>>()

    fun loadSearchResult(query: String, sort: Sort = Sort.Title, filter: Filter = Filter.All) {
        getBlog(
            params = GetBlog.Params(
                query = query,
                sort = sort.toString(),
                page = page++,
                size = size,
                filter = filter
            ),
            scope = viewModelScope,
            onResult = { result: Either<Failure, Search> ->
                result.fold(::handleFailure, ::handleSearchResult)
            }
        )
    }

    private fun handleFailure(failure: Failure) {

    }

    private fun handleSearchResult(searchResult: Search) {
        _searchResult.value = searchResult.documents.map { document ->
            SearchItem(
                typeImage = when (searchResult.type) {
                    is Type.Blog -> R.drawable.ic_launcher_background
                    is Type.Cafe -> R.drawable.ic_launcher_background
                },
                name = document.name,
                title = document.title,
                dateTime = document.dateTime.toString(),
                thumbnail = document.thumbnail.toString()
            )
        }

    }

}