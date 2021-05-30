package com.example.kakao_search.presentation.search.list

import android.net.Uri
import androidx.annotation.DrawableRes

internal sealed class SearchItemView

internal data class SearchItem(
    @DrawableRes val typeImage: Int,
    val name: String,
    val title: String,
    val dateTime: String,
    val thumbnail: Uri
) : SearchItemView()

internal object LoadingItem : SearchItemView()