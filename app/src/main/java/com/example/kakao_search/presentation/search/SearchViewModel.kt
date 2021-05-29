package com.example.kakao_search.presentation.search

import androidx.lifecycle.ViewModel
import com.example.kakao_search.domain.search.GetBlog
import com.example.kakao_search.domain.search.GetCafe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val getBlog: GetBlog,
    private val getCafe: GetCafe

) : ViewModel() {

    fun initialize() {

    }

}