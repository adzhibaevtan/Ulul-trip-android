package com.geeks.ulul.data.remote.apiService.tours

import com.geeks.ulul.data.model.SlugResponse
import retrofit2.Response
import retrofit2.http.GET

interface ToursApiService {

    @GET("home/slugs")
    suspend fun getSlugs(): Response<SlugResponse>

}