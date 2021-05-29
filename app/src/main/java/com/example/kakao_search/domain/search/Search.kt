package com.example.kakao_search.domain.search

import android.net.Uri
import java.time.LocalDateTime


data class Search(
    val type: Type,
    val totalCount: Int,
    val pageCount: Int,
    val isEnd: Boolean,
    val documents: List<Document>,
)

data class Document(
    val title: String,
    val contents: String,
    val url: String,
    val name: String,
    val thumbnail: Uri,
    val dateTime: LocalDateTime,
)

sealed class Type {
    object Blog : Type()
    object Cafe : Type()
}

sealed class Sort {
    object Title : Sort()
    object DateTime : Sort()
}
