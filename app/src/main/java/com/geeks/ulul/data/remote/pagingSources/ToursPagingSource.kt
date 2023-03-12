package com.geeks.ulul.data.remote.pagingSources

import com.geeks.ulul.core.network.paging.BasePagingSource
import com.geeks.ulul.data.model.FilterModel
import com.geeks.ulul.data.model.TourModel
import com.geeks.ulul.data.model.ToursResponse
import com.geeks.ulul.data.remote.apiService.tours.PagingApiService

class ToursPagingSource(private val apiService: PagingApiService, filter: FilterModel) :
    BasePagingSource<ToursResponse, TourModel>({
        apiService.getFilteredTours(
            it,
            category = filter.category,
            date_departure = filter.date_departure,
            complexity = filter.complexity,
            duration = filter.duration,
            price_max = filter.price_max,
        )
    })