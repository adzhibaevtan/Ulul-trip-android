package com.geeks.ulul.repository

import com.geeks.ulul.data.base.BaseRepository
import com.geeks.ulul.data.remote.remoteDataSource.RemoteDataSource
import javax.inject.Inject

class DetailTourRepository @Inject constructor(private val dataSource: RemoteDataSource) : BaseRepository() {

    fun getTourBySlug(slug: String) = doRequest { dataSource.getTourModelBySlug(slug) }
}