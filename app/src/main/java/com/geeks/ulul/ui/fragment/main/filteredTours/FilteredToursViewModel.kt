package com.geeks.ulul.ui.fragment.filteredTours

import androidx.paging.PagingData
import com.geeks.ulul.core.base.BaseViewModel
import com.geeks.ulul.data.model.FilterModel
import com.geeks.ulul.data.model.TourModel
import com.geeks.ulul.data.util.changeFilter
import com.geeks.ulul.repository.FilteredToursRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FilteredToursViewModel @Inject constructor(private val repository: FilteredToursRepository) :
    BaseViewModel() {

    private var filter = FilterModel()
    var getPagingTour = getFilteredTours()

    fun setFilter(newFilter: FilterModel) = filter.changeFilter(newFilter)

    fun getFilteredTours(): Flow<PagingData<TourModel>> = repository.getFilteredTours(filter).gatherPagingRequest { it }
}