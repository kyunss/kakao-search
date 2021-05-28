package com.example.kakao_search.data.entity

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


internal data class BlogEntity(
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