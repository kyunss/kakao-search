package com.example.kakao_search.data.entity

import com.example.kakao_search.domain.search.Search
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


data class CafeEntity(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageCount: Int,
    @SerializedName("is_end") val isEnd: Boolean,
    val documents: List<Document>,

) {
    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        @SerializedName("cafename") val cafeName: String,
        val thumbnail: String,
        @SerializedName("datetime") val dateTime: LocalDateTime,
    )
}

fun CafeEntity.toSearch(): Search {
    return Search(
        totalCount = this.totalCount,
        pageCount = this.pageCount,
        isEnd = this.isEnd,
        documents = this.documents.map {
            Search.Document(
                title = it.title,
                contents = it.contents,
                url = it.url,
                name = it.cafeName,
                thumbnail = it.thumbnail,
                dateTime = it.dateTime
            )
        }
    )
}