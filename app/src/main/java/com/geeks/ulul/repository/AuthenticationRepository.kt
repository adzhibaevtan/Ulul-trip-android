package com.geeks.ulul.repository

import com.geeks.ulul.data.base.BaseRepository
import com.geeks.ulul.data.remote.apiService.auth.AuthenticationApiService
import com.geeks.ulul.data.remote.dto.SignInDto
import com.geeks.ulul.data.remote.dto.SignUpDto
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authenticationApiService: AuthenticationApiService
) : BaseRepository() {

    fun signUp(
        name: String,
        username: String,
        email: String,
        password: String,
        password_again: String
    ) = makeNetworkRequest {
        authenticationApiService.signUp(
            SignUpDto(
                name,
                username,
                email,
                password,
                password_again
            )
        )
    }

    fun signIn(username: String, password: String) = makeNetworkRequest {
        authenticationApiService.login(SignInDto(username, password))
    }
}