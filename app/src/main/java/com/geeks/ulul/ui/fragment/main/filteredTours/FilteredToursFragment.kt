package com.geeks.ulul.ui.fragment.main.filteredTours

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.data.model.FilterModel
import com.geeks.ulul.data.model.TourModel
import com.geeks.ulul.ui.fragment.filteredTours.FilteredToursViewModel
import com.geeks.ulul.ui.fragment.main.filteredTours.adapter.FilteredToursAdapter
import com.geeks.ulul.ui.fragment.main.search.SearchFragment.Companion.KEY_FILTER
import com.geeks.ulul_trip_android.R
import com.geeks.ulul_trip_android.databinding.FragmentFilteredToursBinding
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class FilteredToursFragment :
    BaseFragment<FragmentFilteredToursBinding, FilteredToursViewModel>(R.layout.fragment_filtered_tours) {

    override val binding by viewBinding(FragmentFilteredToursBinding::bind)
    override val viewModel by viewModels<FilteredToursViewModel>()
    private val filter by lazy { arguments?.getSerializable(KEY_FILTER) as FilterModel }
    private val filteredToursAdapter by lazy { FilteredToursAdapter(this::onTourClick) }

    override fun initialize() {
        super.initialize()
        initAdapter()
    }

    private fun initAdapter() {
        binding.rvTours.adapter = filteredToursAdapter
    }

    override fun initRequest() {
        super.initRequest()
        getTours()
    }

    override fun initSubscribers() {
        super.initSubscribers()
        collectTours()
    }

    private fun collectTours() {
        viewModel.getPagingTour.spectatePaging { filteredToursAdapter.submitData(it) }
    }

    private fun getTours() {
        viewModel.setFilter(filter)
        viewModel.getFilteredTours()
    }

    private fun onTourClick(model: TourModel) {
        model.guide.photo = "photo" // add photo into database
        findNavController().navigate(
            R.id.detailTourFragment, bundleOf(KEY_DETAIL_TOUR to model)
        )
    }

    companion object {
        const val KEY_DETAIL_TOUR = "key.detail.tour"
    }
}