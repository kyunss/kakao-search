package com.example.kakao_search.presentation.search.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kakao_search.databinding.ViewholderSearchBinding


internal class SearchViewHolder(private val binding: ViewholderSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): SearchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            return SearchViewHolder(ViewholderSearchBinding.inflate(layoutInflater, parent, false))
        }
    }

    fun bind(searchItem: SearchItem, listener: (SearchItem) -> Unit) {
        itemView.setOnClickListener { listener(searchItem) }

        binding.searchItem = searchItem

        binding.executePendingBindings()
    }


}