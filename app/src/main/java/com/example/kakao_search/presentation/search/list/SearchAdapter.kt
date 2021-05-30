package com.example.kakao_search.presentation.search.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


internal class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    interface Listener {
        fun onItemClicked(searchItem: SearchItem)
        fun onBottomReached(position: Int)
    }

    private var searchResultList = listOf<SearchItem>()

    private var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent, object : SearchViewHolder.Listener {
            override fun onItemClicked(searchItem: SearchItem) {
                listener?.onItemClicked(searchItem)
            }
        })
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResultList[position])

        if (position == searchResultList.size - 1) {
            listener?.onBottomReached(position)
        }
    }

    override fun getItemCount() = searchResultList.size

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun setSearchResultList(list: List<SearchItem>) {
        this.searchResultList = list

        notifyDataSetChanged()
    }

}

