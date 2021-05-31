package com.example.kakao_search.domain.detail

import android.net.Uri
import com.example.kakao_search.domain.search.Filter
import java.time.LocalDateTime


data class SearchDetail(
    val type: Filter.Type,
    val thumbnail: Uri,
    val name: String,
    val title: String,
    val contents: String,
    val dateTime: LocalDateTime,
    val url: String,
)
