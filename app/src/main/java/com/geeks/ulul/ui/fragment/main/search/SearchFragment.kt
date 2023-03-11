package com.geeks.ulul.ui.fragment.main.search

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.core.extention.navigateSafely
import com.geeks.ulul.data.model.FilterModel
import com.geeks.ulul.data.model.SlugModel
import com.geeks.ulul.ui.fragment.main.search.adapter.HintAdapter
import com.geeks.ulul_trip_android.R

import com.geeks.ulul_trip_android.databinding.FragmentSearchBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel by viewModels<SearchViewModel>()
    private val filter by lazy { FilterModel() }
    private val hintAdapter by lazy { HintAdapter(this::onHintClick) }

    private val hintsList = arrayListOf<SlugModel>()
    private val maybeIt = arrayListOf<SlugModel>()

    override fun initialize() {
        super.initialize()
        binding.rvHints.adapter = hintAdapter
    }

    override fun initListeners() {
        super.initListeners()

        listenHints()
        setDuration()
        setPrice()
        setDeparture()
        setComplexity()
        setCategory()
        onClickClear()
        search()
    }

    override fun initRequest() {
        super.initRequest()
        getSlugs()
    }

    override fun initSubscribers() {
        super.initSubscribers()
        getSlugsState()
    }

    private fun listenHints() {
        binding.etTourTitle.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                for (hint in hintsList) {
                    if (hint.title.lowercase().contains(
                            it.toString().lowercase()
                        ) && !maybeIt.contains(hint) && maybeIt.size < 3
                    ) {
                        maybeIt.add(hint)
                    }
                }
                hintAdapter.addData(maybeIt)
                maybeIt.clear()
            }
        }
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

    private fun onClickClear() {
        binding.btnClear.setOnClickListener {
            with(filter) {
                complexity = ""
                duration = ""
            }
            with(binding) {
                etMaxPrice.text.clear()
                etCategory.text.clear()
                etDeparture.text.clear()
                etTourTitle.text.clear()
            }
        }
    }

    private fun search() {
        binding.btnSearch.setOnClickListener {
//            findNavController().navigate(R.id.toursFragment, bundleOf(KEY_FILTER to filter))
        }
    }

    private fun getSlugsState() {
        viewModel.getSlugsState.collectUIState {
            hintsList.addAll(it.results)
        }
    }

    private fun getSlugs() {
        viewModel.getSlugs()
    }

    private fun onHintClick(tourSlug: String) {
        Toast.makeText(requireContext(), tourSlug, Toast.LENGTH_SHORT).show()
//        findNavController().navigateSafely(R.id.)
//        findNavController().navigate(R.id.detailFragment, bundleOf(KEY_SlUG to tourSlug))
    }

    companion object {
        const val KEY_FILTER = "key.filter"
        const val KEY_SlUG = "key.slug"
    }
}