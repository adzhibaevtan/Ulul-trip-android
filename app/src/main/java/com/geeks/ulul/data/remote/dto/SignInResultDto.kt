package com.geeks.ulul.data.remote.dto

data class SignInResultDto(
    val email: String,
    val tokens: String,
    val username: String
)