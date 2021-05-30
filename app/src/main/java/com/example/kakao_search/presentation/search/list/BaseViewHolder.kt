package com.example.kakao_search.presentation.search.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView


internal abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var currentPosition = 0

    protected abstract fun clear()

    fun onBind(position: Int) {
        currentPosition = position

        clear()
    }
}