package com.example.kakao_search.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakao_search.databinding.FragmentSearchBinding
import com.example.kakao_search.presentation.search.list.PagingListener
import com.example.kakao_search.presentation.search.list.SearchAdapter
import com.example.kakao_search.presentation.search.list.SearchItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var binding: FragmentSearchBinding

    private val searchAdapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
        initializeListener()
        observeViewModel()
    }

    private fun initializeView() {
        binding.rvSearchResult.adapter = searchAdapter
        binding.rvSearchResult.layoutManager = LinearLayoutManager(context)
    }

    private fun initializeListener() {
        binding.bSearch.setOnClickListener {
            hideKeyboard()

            val query = binding.etQuery.text.toString()

            viewModel.loadSearchResult(query)
        }

        binding.rvSearchResult.addOnScrollListener(object : PagingListener(binding.rvSearchResult.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.loadMoreItems()
            }
        })

        searchAdapter.clickListener = { searchItem ->
            viewModel.onSearchItemClicked(searchItem)
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            searchResult.observe(viewLifecycleOwner, { searchItemList ->
                searchAdapter.addSearchResult(searchItemList)
            })

            clearSearchResult.observe(viewLifecycleOwner, {
                searchAdapter.clear()
            })

            noSearchResult.observe(viewLifecycleOwner, { noResult ->
                binding.rvSearchResult.isVisible = !noResult

                binding.tvNoSearch.isVisible = noResult
            })
        }
    }

    private fun hideKeyboard() {
        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(binding.etQuery.windowToken, 0)
    }

}