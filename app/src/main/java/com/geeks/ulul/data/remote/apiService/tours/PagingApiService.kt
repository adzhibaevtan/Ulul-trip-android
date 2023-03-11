package com.geeks.ulul.data.remote.apiService.tours

import com.geeks.ulul.data.model.ToursResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PagingApiService {

    @GET("home/tours/")
    suspend fun getFilteredTours(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 1,
        @Query("category") category: String,
        @Query("date_departure") date_departure: String,
        @Query("complexity") complexity: String,
        @Query("duration") duration: String,
        @Query("price_max") price_max: String,
    ): Response<ToursResponse>
}