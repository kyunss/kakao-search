package com.example.kakao_search.presentation.detail

import android.net.Uri


data class SearchDetailView(
    val type: String,
    val thumbnail: Uri,
    val name: String,
    val title: String,
    val contents: String,
    val dateTime: String,
    val url: String,
)