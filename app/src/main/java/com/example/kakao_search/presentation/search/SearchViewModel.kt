package com.example.kakao_search.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakao_search.R
import com.example.kakao_search.domain.search.*
import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import com.example.kakao_search.presentation.search.list.SearchItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val getSearch: GetSearch
) : ViewModel() {

    private var page = 1
    private var lastQuery = ""
    private var lastFilter: Filter = Filter.All

    val searchResult: LiveData<List<SearchItem>>
        get() = _searchResult
    private val _searchResult = MutableLiveData<List<SearchItem>>()

    val clearSearchResult: LiveData<Boolean>
        get() = _clearSearchResult
    private val _clearSearchResult = MutableLiveData(false)

    val noSearchResult: LiveData<Boolean>
        get() = _noSearchResult
    private val _noSearchResult = MutableLiveData(false)

    fun loadSearchResult(query: String, filter: Filter = Filter.Type.Blog) {
        this.lastQuery = query
        this.lastFilter = filter

        _clearSearchResult.value = true

        getSearch(
            params = GetSearch.Params(
                query = query,
                page = page,
                filter = filter
            ),
            scope = viewModelScope,
            onResult = { result: Either<Failure, Search> ->
                result.fold(::handleFailure, ::handleSearchResult)
            }
        )
    }

    fun onSearchItemClicked(searchItem: SearchItem) {

    }

    fun loadMoreItems() {
        getSearch(
            params = GetSearch.Params(
                query = this.lastQuery,
                page = ++page,
                filter = this.lastFilter
            ),
            scope = viewModelScope,
            onResult = { result: Either<Failure, Search> ->
                result.fold(::handleFailure, ::handleSearchResult)
            }
        )

    }

    private fun handleFailure(failure: Failure) {
        Log.e("SearchViewModel", "$failure")
    }

    private fun handleSearchResult(searchResult: Search) {
        Log.e("SearchViewModel", "searchResultSize: ${searchResult.documents.size}, pageNumber : ${this.page}")

        _searchResult.value = searchResult.documents.map { document ->
            SearchItem(
                typeImage = when (document.type) {
                    is Filter.Type.Blog -> R.drawable.ic_round_format_bold_24
                    is Filter.Type.Cafe -> R.drawable.ic_baseline_local_cafe_24
                },
                name = document.name,
                title = document.title,
                dateTime = document.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE),
                thumbnail = document.thumbnail
            )
        }

        _noSearchResult.value = searchResult.documents.isEmpty()
    }

}