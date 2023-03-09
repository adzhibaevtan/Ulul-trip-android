package com.geeks.ulul.remote.apiService

import com.geeks.ulul.remote.dto.SignInDto
import com.geeks.ulul.remote.dto.SignUpDto
import com.geeks.ulul.remote.dto.SignInResultDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST("users/register/")
    suspend fun signUp(@Body signUpDto: SignUpDto)

    @POST("users/login/")
    suspend fun login(@Body signInDto: SignInDto): SignInResultDto

}