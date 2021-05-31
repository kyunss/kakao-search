package com.example.kakao_search.data.dataSource

import com.example.kakao_search.domain.search.Search
import javax.inject.Inject


internal class SearchResultCache @Inject constructor() {

    var searchResult: Search = Search.empty()

}