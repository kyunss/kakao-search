package com.example.kakao_search.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakao_search.databinding.FragmentSearchBinding
import com.example.kakao_search.presentation.search.list.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var binding: FragmentSearchBinding

    private val searchAdapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

        searchAdapter.clickListener = { searchItem ->

        }
    }

    private fun initializeListener() {
        binding.bSearch.setOnClickListener {
            val query = binding.etQuery.text.toString()

            viewModel.loadSearchResult(query)
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            searchResult.observe(viewLifecycleOwner, { searchItemList ->
                searchAdapter.setSearchResultList(searchItemList)
            })
        }
    }


}