package com.example.kakao_search.presentation.search.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kakao_search.databinding.ViewholderSearchBinding


internal class SearchViewHolder(private val binding: ViewholderSearchBinding, private val listener: Listener) : RecyclerView.ViewHolder(binding.root) {

    interface Listener {
        fun onItemClicked(searchItem: SearchItem)
    }

    companion object {
        fun from(parent: ViewGroup, listener: Listener): SearchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            return SearchViewHolder(ViewholderSearchBinding.inflate(layoutInflater, parent, false), listener)
        }
    }

    fun bind(searchItem: SearchItem) {
        itemView.setOnClickListener { listener.onItemClicked(searchItem) }

        binding.searchItem = searchItem

        binding.executePendingBindings()
    }

}