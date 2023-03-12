package com.geeks.ulul.repository

import androidx.paging.PagingData
import com.geeks.ulul.data.base.BaseRepository
import com.geeks.ulul.data.model.FilterModel
import com.geeks.ulul.data.model.TourModel
import com.geeks.ulul.data.remote.apiService.tours.PagingApiService
import com.geeks.ulul.data.remote.pagingSources.ToursPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilteredToursRepository @Inject constructor(private val apiService: PagingApiService) :
    BaseRepository() {

    fun getFilteredTours(filter: FilterModel): Flow<PagingData<TourModel>> {
        return doPagingRequest(ToursPagingSource(apiService, filter), pageSize = 10)
    }
}