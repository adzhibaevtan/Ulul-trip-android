package com.geeks.ulul.ui.fragment.main.profile.favoriteTours

import com.geeks.ulul.core.base.BaseViewModel
import com.geeks.ulul.data.remote.dto.dtoo.FavoriteTourDto
import com.geeks.ulul.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteToursViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val _favoriteToursState = mutableUiStateFlow<List<FavoriteTourDto>>()
    val favoriteToursState = _favoriteToursState.asStateFlow()

    private fun fetchFavoriteTours() =
        userRepository.getFavoriteTours().gatherRequest(_favoriteToursState)

    init {
        fetchFavoriteTours()
    }
}