package com.example.kakao_search.presentation.search.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


internal class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private var searchResultList = listOf<SearchItem>()

    internal var clickListener: (SearchItem) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResultList[position], clickListener)
    }

    override fun getItemCount() = searchResultList.size

    fun setSearchResultList(list: List<SearchItem>) {
        this.searchResultList = list

        notifyDataSetChanged()
    }

}