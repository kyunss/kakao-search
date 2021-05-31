package com.example.kakao_search.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.htmlEncode
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kakao_search.R
import com.example.kakao_search.databinding.FragmentDetailBinding
import com.example.kakao_search.presentation.search.SearchFragmentDirections
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

        initializeListener()

        viewModel.searchDetailView.observe(viewLifecycleOwner, { searchDetailView ->
            binding.tvDetailType.text = searchDetailView.type

            Glide.with(binding.ivDetailThumbnail)
                .load(searchDetailView.thumbnail)
                .error(R.drawable.ic_baseline_disabled_by_default_24)
                .into(binding.ivDetailThumbnail)

            binding.tvDetailName.text = searchDetailView.name.parseAsHtml()
            binding.tvDetailTitle.text = searchDetailView.title.parseAsHtml()
            binding.tvDetailContents.text = searchDetailView.contents.parseAsHtml()
            binding.tvDetailDatetime.text = searchDetailView.dateTime
            binding.tvDetailUrlLink.text = searchDetailView.url

            binding.ivDetailThumbnail
        })
    }

    private fun initializeListener() {
        binding.ivDetailBack.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToSearchFragment()

            findNavController().navigate(action)
        }
    }

}