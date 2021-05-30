package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.search.Search
import javax.inject.Inject


internal class SearchResultCache @Inject constructor() {

    lateinit var searchResult: Search

}