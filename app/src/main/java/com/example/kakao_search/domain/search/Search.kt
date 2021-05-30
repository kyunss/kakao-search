package com.example.kakao_search.domain.search

import android.net.Uri
import java.time.LocalDateTime


data class Search(
    val totalCount: Int,
    val pageCount: Int,
    val isEnd: Boolean,
    val documents: List<Document>,
) {
    operator fun plus(other: Search): Search {
        return Search(
            totalCount = this.totalCount + other.totalCount,
            pageCount = this.pageCount + other.pageCount,
            isEnd = this.isEnd || other.isEnd,
            documents = this.documents + other.documents
        )
    }
}

data class Document(
    val type: Filter.Type,
    val thumbnail: Uri,
    val name: String,
    val title: String,
    val contents: String,
    val dateTime: LocalDateTime,
    val url: String,
)

sealed class Filter {
    object All : Filter()

    sealed class Type : Filter() {
        object Blog : Type()
        object Cafe : Type()
    }
}

sealed class Sort {
    object Title : Sort()
    object DateTime : Sort()
}
