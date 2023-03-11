package com.geeks.ulul.data.remote.remoteDataSource

import com.geeks.ulul.core.network.baseDataSource.BaseDataSource
import com.geeks.ulul.data.remote.apiService.tours.ToursApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService : ToursApiService) : BaseDataSource(){

    suspend fun getSlugs() = getResult { apiService.getSlugs() }

    suspend fun getTourModelBySlug(slug: String) = getResult { apiService.getTourBySlug(slug) }
}