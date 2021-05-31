package com.example.kakao_search.data.entity

import android.net.Uri
import com.example.kakao_search.domain.search.Document
import com.example.kakao_search.domain.search.Filter
import com.example.kakao_search.domain.search.Search
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class CafeEntity(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageCount: Int,
    @SerializedName("is_end") val isEnd: Boolean,
    val documents: List<Document>,

    ) {
    companion object {
        fun empty(): CafeEntity {
            return CafeEntity(
                totalCount = 0,
                pageCount = 0,
                isEnd = false,
                documents = emptyList()
            )
        }
    }

    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        @SerializedName("cafename") val cafeName: String,
        val thumbnail: String,
        @SerializedName("datetime") val dateTime: String,
    )
}

fun CafeEntity.toSearch(): Search {
    return Search(
        totalCount = this.totalCount,
        pageCount = this.pageCount,
        isEnd = this.isEnd,
        documents = this.documents.map {
            Document(
                type = Filter.Type.Cafe,
                title = it.title,
                contents = it.contents,
                url = it.url,
                name = it.cafeName,
                thumbnail = Uri.parse(it.thumbnail),
                dateTime = LocalDateTime.parse(it.dateTime, DateTimeFormatter.ISO_DATE_TIME)
            )
        }
    )
}