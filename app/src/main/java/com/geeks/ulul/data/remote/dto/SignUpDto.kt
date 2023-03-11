package com.geeks.ulul.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SignUpDto(
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("password_again")
    val password_again: String
)