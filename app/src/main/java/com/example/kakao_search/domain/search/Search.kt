package com.example.kakao_search.domain.search

import java.time.LocalDateTime


data class Search(
    val type: Type,
    val totalCount: Int,
    val pageCount: Int,
    val isEnd: Boolean,
    val documents: List<Document>,
    val filter: Filter
)

data class Document(
    val title: String,
    val contents: String,
    val url: String,
    val name: String,
    val thumbnail: String,
    val dateTime: LocalDateTime,
)

sealed class Type {
    object Blog : Type()
    object Cafe : Type()
}

sealed class Filter {
    object All : Filter()
    object Blog : Filter()
    object Cafe : Filter()
}

sealed class Sort {
    object Title : Sort()
    object DateTime : Sort()
}
