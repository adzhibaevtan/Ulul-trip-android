package com.geeks.ulul.ui.fragment.main.search

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.data.model.FilterModel
import com.geeks.ulul_trip_android.R

import com.geeks.ulul_trip_android.databinding.FragmentSearchBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel by viewModels<SearchViewModel>()
    private val filter by lazy { FilterModel() }

    override fun initListeners() {
        super.initListeners()
        setDuration()
        setPrice()
        setDeparture()
        setComplexity()
        setCategory()
    }

    private fun setDuration() {
        with(binding) {
            btnOneDay.setOnClickListener {
                filter.duration = "1"
            }
            btnThreeDay.setOnClickListener {
                filter.duration = "3"
            }
            btnSevenDay.setOnClickListener {
                filter.duration = "7"
            }
        }
    }

    private fun setPrice() {
        with(binding) {
            etMaxPrice.doAfterTextChanged {
                filter.price_max = it.toString()
            }
        }
    }

    private fun setDeparture() {
        binding.etDeparture.doAfterTextChanged {
            filter.date_departure = it.toString()
        }
    }

    private fun setComplexity() {
        with(binding) {
            btnEasy.setOnClickListener {
                filter.complexity = "Easy"
            }
            btnMedium.setOnClickListener {
                filter.complexity = "Medium"
            }
            btnHard.setOnClickListener {
                filter.complexity = "Hard"
            }
            btnExtra.setOnClickListener {
                filter.complexity = "Extra"
            }
        }
    }

    private fun setCategory() {
        binding.etCategory.doAfterTextChanged {
            filter.category = it.toString()
        }
    }
}