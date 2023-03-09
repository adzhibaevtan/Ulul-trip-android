package com.geeks.ulul.ui.fragment.main.search

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul_trip_android.R

import com.geeks.ulul_trip_android.databinding.FragmentSearchBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel by viewModels<SearchViewModel>()
}