package com.geeks.ulul.repository

import com.geeks.ulul.data.base.BaseRepository
import com.geeks.ulul.remote.apiService.AuthenticationApiService
import com.geeks.ulul.remote.dto.SignInDto
import com.geeks.ulul.remote.dto.SignUpDto
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authenticationApiService: AuthenticationApiService
) : BaseRepository() {

    fun signUp(
        first_name: String,
        last_name: String,
        email: String,
        password: String,
        password_again: String
    ) = makeNetworkRequest {
        authenticationApiService.signUp(
            SignUpDto(
                first_name,
                last_name,
                email,
                password,
                password_again
            )
        )
    }

    fun signIn(email: String, password: String) = makeNetworkRequest {
        authenticationApiService.login(SignInDto(email, password))
    }
}