package com.example.kakao_search.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.kakao_search.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class DetailFragment : Fragment() {

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: FragmentDetailBinding

    private val arguments: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadSearchDetail(arguments.index)

        viewModel.searchDetailView.observe(viewLifecycleOwner, {
            Log.d("DetailFragment", "$it")
        })
    }

}