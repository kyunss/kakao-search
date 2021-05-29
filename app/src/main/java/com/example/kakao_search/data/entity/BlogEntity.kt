package com.example.kakao_search.data.entity

import com.example.kakao_search.domain.search.Document
import com.example.kakao_search.domain.search.Search
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


data class BlogEntity(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageCount: Int,
    @SerializedName("is_end") val isEnd: Boolean,
    val documents: List<Document>,

    ) {
    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        @SerializedName("blogname") val blogName: String,
        val thumbnail: String,
        @SerializedName("datetime") val dateTime: LocalDateTime,
    )
}

fun BlogEntity.toSearch(): Search {
    return Search(
        totalCount = this.totalCount,
        pageCount = this.pageCount,
        isEnd = this.isEnd,
        documents = this.documents.map {
            Document(
                title = it.title,
                contents = it.contents,
                url = it.url,
                name = it.blogName,
                thumbnail = it.thumbnail,
                dateTime = it.dateTime
            )
        }
    )
}