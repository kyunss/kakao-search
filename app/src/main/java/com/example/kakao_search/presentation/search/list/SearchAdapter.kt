package com.example.kakao_search.presentation.search.list

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


internal class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private var searchResultList = mutableListOf<SearchItem>()

    internal var clickListener: (SearchItem) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResultList[position], clickListener)
    }

    override fun getItemCount() = searchResultList.size

    fun addSearchResult(list: List<SearchItem>) {
        this.searchResultList.addAll(list)
        Log.d("Adapter", "SearchResultSize : ${this.searchResultList.size}")

        notifyDataSetChanged()
    }

    fun clear() {
        this.searchResultList.clear()

        notifyDataSetChanged()
    }

}