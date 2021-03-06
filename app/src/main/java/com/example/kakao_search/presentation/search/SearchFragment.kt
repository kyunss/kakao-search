package com.example.kakao_search.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakao_search.databinding.FragmentSearchBinding
import com.example.kakao_search.presentation.core.BaseFragment
import com.example.kakao_search.presentation.search.list.PagingListener
import com.example.kakao_search.presentation.search.list.SearchAdapter
import com.example.kakao_search.support.extension.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class SearchFragment : BaseFragment() {

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var binding: FragmentSearchBinding

    private val searchAdapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun initializeViews() {
        binding.rvSearchResult.adapter = searchAdapter
        binding.rvSearchResult.layoutManager = LinearLayoutManager(context)
    }

    override fun initializeListeners() {
        binding.bSearch.setOnClickListener {
            requireContext().hideKeyboard(binding.etQuery)

            val query = binding.etQuery.text.toString()

            viewModel.loadSearchResult(query)
        }

        binding.rvSearchResult.addOnScrollListener(object : PagingListener(binding.rvSearchResult.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.loadMoreItems()
            }
        })

        searchAdapter.clickListener = { searchItem, position ->
            viewModel.onSearchItemClicked(searchItem, position)
        }
    }

    override fun observeViewModel() {
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

            navigateToDetail.observe(viewLifecycleOwner, { position ->
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(index = position)

                findNavController().navigate(action)
            })
        }
    }

}