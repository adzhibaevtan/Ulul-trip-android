package com.geeks.ulul.ui.fragment.main.profile.favoriteTours

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.ulul.core.base.BaseFragment
import com.geeks.ulul.data.remote.dto.dtoo.FavoriteTourDto
import com.geeks.ulul_trip_android.R
import com.geeks.ulul_trip_android.databinding.FragmentFavoriteToursBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteToursFragment :
    BaseFragment<FragmentFavoriteToursBinding, FavoriteToursViewModel>(R.layout.fragment_favorite_tours) {
    override val binding by viewBinding(FragmentFavoriteToursBinding::bind)
    override val viewModel by viewModels<FavoriteToursViewModel>()
    private val favoriteToursAdapter = FavoriteToursAdapter(this::onTourClick)

    override fun initialize() {
        binding.rvTours.adapter = favoriteToursAdapter

        viewModel.favoriteToursState.spectateUiState(success = {
            safeFlowGather {
                favoriteToursAdapter.submitData(PagingData.from(it))
            }
        })
    }

    private fun onTourClick(favoriteTour: FavoriteTourDto) {
        findNavController().navigate(
            R.id.action_favoriteToursFragment_to_detailTourFragment,
            bundleOf(KEY_DETAIL_TOUR_FAVORITE to favoriteTour)
        )
    }

    companion object {
        const val KEY_DETAIL_TOUR_FAVORITE = "keyDetailTourFavorite"
    }
}