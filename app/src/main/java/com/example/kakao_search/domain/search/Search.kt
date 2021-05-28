package com.example.kakao_search.domain.search

import java.time.LocalDateTime


internal data class Search(
    val totalCount: Int,
    val pageCount: Int,
    val isEnd: Boolean,
    val documents: List<Document>
) {
    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        val name: String,
        val thumbnail: String,
        val dateTime: LocalDateTime,
    )
}