package com.geeks.ulul.data.remote.apiService.user

import com.geeks.ulul.data.remote.dto.dtoo.FavoriteTourResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("profiles/{id}/favorites")
    suspend fun fetchFavorites(@Path("id") id: Int): FavoriteTourResponse

    @DELETE("profiles/profile/{id}/")
    suspend fun deleteAccount(@Path("id") id: Int)
}