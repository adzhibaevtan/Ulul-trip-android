package com.geeks.ulul.data.remote.apiService.tours

import com.geeks.ulul.data.model.SlugResponse
import com.geeks.ulul.data.model.TourModelBySlug
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ToursApiService {

    @GET("home/slugs")
    suspend fun getSlugs(): Response<SlugResponse>

    @GET("home/tours/{slug}")
    suspend fun getTourBySlug(
        @Path("slug") slug: String,
    ): Response<TourModelBySlug>
}