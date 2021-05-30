package com.example.kakao_search.support.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}