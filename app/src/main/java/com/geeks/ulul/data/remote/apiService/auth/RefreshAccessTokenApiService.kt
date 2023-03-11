package com.geeks.ulul.data.remote.apiService.auth

import com.geeks.ulul.data.remote.dto.RefreshTokenDto
import com.geeks.ulul.data.remote.dto.TokensDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshAccessTokenApiService {

    @POST("users/refresh/")
    fun refreshTokens(@Body refreshToken: RefreshTokenDto): Call<TokensDto>
}