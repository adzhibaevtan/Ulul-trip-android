package com.geeks.ulul.remote.dto

import com.google.gson.annotations.SerializedName

data class SignInDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)