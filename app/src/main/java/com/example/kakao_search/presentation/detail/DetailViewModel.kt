package com.example.kakao_search.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.kakao_search.domain.detail.GetSearchDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val getSearchDetail: GetSearchDetail
) : ViewModel() {

    

}