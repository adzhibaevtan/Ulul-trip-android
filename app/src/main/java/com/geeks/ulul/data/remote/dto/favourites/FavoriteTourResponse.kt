package com.geeks.ulul.data.remote.dto.dtoo


import com.google.gson.annotations.SerializedName

data class FavoriteTourResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("favorite_tour")
    val favoriteTour: List<FavoriteTourDto>
)