package com.geeks.ulul.remote.apiService

import com.geeks.ulul.remote.dto.RefreshTokenDto
import com.geeks.ulul.remote.dto.TokensDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshAccessTokenApiService {

    @POST("users/refresh/")
    fun refreshTokens(@Body refreshToken: RefreshTokenDto): Call<TokensDto>
}