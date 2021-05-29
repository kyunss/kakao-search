package com.example.kakao_search.presentation.search.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


internal class SearchAdapter(private val listener: SearchItemListener) : RecyclerView.Adapter<SearchViewHolder>() {

    private var searchResultList = listOf<SearchItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResultList[position], listener)
    }

    override fun getItemCount() = searchResultList.size

    fun setSearchResultList(list: List<SearchItem>) {
        this.searchResultList = list

        notifyDataSetChanged()
    }

}

internal class SearchItemListener(val listener: (SearchItem) -> Unit) {
    fun onItemClicked(searchItem: SearchItem) = listener(searchItem)
}