package com.geeks.ulul.remote

import com.geeks.ulul.data.local.pref.UserPreferences

import com.geeks.ulul.remote.apiService.RefreshAccessTokenApiService
import com.geeks.ulul.remote.dto.RefreshTokenDto
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class Authenticator @Inject constructor(
    private val refreshAccessTokenApiService: RefreshAccessTokenApiService,
    private val userDataPreferencesManager: UserPreferences
) : okhttp3.Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            val tokenResponse = getRefreshedToken()?.execute()
            return when {
                tokenResponse?.isSuccessful == true -> {
                    userDataPreferencesManager.accessToken = tokenResponse.body()?.access
                    userDataPreferencesManager.refreshToken = tokenResponse.body()?.refresh
                    response.request.newBuilder()
                        .addHeader("Authorization", "Bearer ${tokenResponse.body()}")
                        .build()
                }
                tokenResponse?.code() == 401 -> {
                    getRefreshedToken()
                    null
                }
                tokenResponse?.code() == 400 -> null
                else -> null
            }
        }
    }

    private fun getRefreshedToken() =
        userDataPreferencesManager.refreshToken?.let {
            refreshAccessTokenApiService.refreshTokens(
                RefreshTokenDto(it)
            )
        }
}