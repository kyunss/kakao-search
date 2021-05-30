package com.example.kakao_search.data.entity

import android.net.Uri
import com.example.kakao_search.domain.search.*
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class BlogEntity(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageCount: Int,
    @SerializedName("is_end") val isEnd: Boolean,
    val documents: List<Document>,
) {

    companion object {
        fun empty(): BlogEntity {
            return BlogEntity(
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
        @SerializedName("blogname") val blogName: String,
        val thumbnail: String,
        @SerializedName("datetime") val dateTime: String,
    )
}

fun BlogEntity.toSearch(): Search {
    return Search(
        totalCount = this.totalCount,
        pageCount = this.pageCount,
        isEnd = this.isEnd,
        documents = this.documents.map {
            Document(
                type = Filter.Type.Blog,
                title = it.title,
                contents = it.contents,
                url = it.url,
                name = it.blogName,
                thumbnail = Uri.parse(it.thumbnail),
                dateTime = LocalDateTime.parse(it.dateTime, DateTimeFormatter.ISO_DATE_TIME)
            )
        }
    )
}