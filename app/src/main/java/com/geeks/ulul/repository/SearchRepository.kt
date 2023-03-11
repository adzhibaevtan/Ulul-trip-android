package com.geeks.ulul.repository

import com.geeks.ulul.data.base.BaseRepository
import com.geeks.ulul.data.remote.remoteDataSource.RemoteDataSource
import javax.inject.Inject

class SearchRepository @Inject constructor(private val dataSource: RemoteDataSource) : BaseRepository() {

    fun getSlugs() = doRequest { dataSource.getSlugs() }
}