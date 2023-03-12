package com.geeks.ulul.ui.fragment.main.detailTour

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.data.model.TourModel
import com.geeks.ulul.data.util.getRegions
import com.geeks.ulul.data.util.share
import com.geeks.ulul.ui.fragment.main.detailTour.adapter.DetailTourImagesAdapter
import com.geeks.ulul.ui.fragment.main.filteredTours.FilteredToursFragment.Companion.KEY_DETAIL_TOUR
import com.geeks.ulul.ui.fragment.main.search.SearchFragment.Companion.KEY_SlUG
import com.geeks.ulul_trip_android.R
import com.geeks.ulul_trip_android.databinding.FragmentDetailTourBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailTourFragment :
    BaseFragment<FragmentDetailTourBinding, DetailTourViewModel>(R.layout.fragment_detail_tour) {

    override val binding by viewBinding(FragmentDetailTourBinding::bind)
    override val viewModel by viewModels<DetailTourViewModel>()
    private val detailTourAdapter by lazy { DetailTourImagesAdapter() }
    private var model: TourModel? = null

    override fun initialize() {
        super.initialize()
        binding.vpImages.adapter = detailTourAdapter
    }

    override fun assembleViews() {
        super.assembleViews()
        getModel()
    }

    override fun initListeners() {
        super.initListeners()
        share()
        reserve()
    }

    private fun getModel() {
        if (arguments?.getSerializable(KEY_SlUG) != null) {
            viewModel.getTourBySlug(arguments?.getSerializable(KEY_SlUG) as String)
            viewModel.getTourBySlugState.collectUIState {
                setData(it[0])
            }
        } else {
            model = arguments?.getSerializable(KEY_DETAIL_TOUR) as TourModel
            setData(model!!)
        }
    }

    private fun setData(model: TourModel) {
        with(binding) {
            tvTitle.text = model.title
            detailTourAdapter.addData(model.tour_images)
            tvCategory.text = model.category.name
            tvRegion.text = model.region.getRegions()
            tvComplexity.text = model.complexity
            tvDuration.text = model.duration
            tvPrice.text = model.price.toString()
            tvDeparture.text = model.date_departure
            tvArrival.text = model.date_arrival
            tvGuide.text = model.guide.get_initials
            tvDescription.text = model.description
        }
    }

    private fun share() {
        with(binding) {
            ivShare.setOnClickListener {
                startActivity(Intent().share(tvTitle.text.toString()))
            }
        }
    }

    private fun reserve() {
        binding.btnReserve.setOnClickListener {
            val telegramBot = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/BotFather"))
            startActivity(telegramBot)
        }
    }
}