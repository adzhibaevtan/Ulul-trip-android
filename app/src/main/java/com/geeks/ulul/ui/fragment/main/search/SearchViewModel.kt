package com.geeks.ulul.ui.fragment.main.search

import com.geeks.ulul.core.base.BaseViewModel
import com.geeks.ulul.data.model.SlugResponse
import com.geeks.ulul.repository.SearchRepository
import com.geeks.ulul.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository
) : BaseViewModel() {

    private val _getSlugsState = MutableStateFlow<UIState<SlugResponse>>(UIState.Idle())
    val getSlugsState = _getSlugsState.asStateFlow()

    fun getSlugs() = repository.getSlugs().collectFlow(_getSlugsState)
}