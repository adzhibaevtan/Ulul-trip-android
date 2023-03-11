package com.geeks.ulul.ui.fragment.main.detailTour

import com.geeks.ulul.core.base.BaseViewModel
import com.geeks.ulul.data.model.TourModelBySlug
import com.geeks.ulul.repository.DetailTourRepository
import com.geeks.ulul.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailTourViewModel @Inject constructor(private val repository: DetailTourRepository) :
    BaseViewModel() {

    private val _getTourBySlugState = MutableStateFlow<UIState<TourModelBySlug>>(UIState.Idle())
    val getTourBySlugState = _getTourBySlugState.asStateFlow()

    fun getTourBySlug(slug: String) = repository.getTourBySlug(slug).collectFlow(_getTourBySlugState)
}