package com.geeks.ulul.remote


import com.geeks.ulul.remote.NetworkFastBuilder.Companion.provideOkHttpClientBuilder
import com.geeks.ulul.remote.NetworkFastBuilder.Companion.provideRetrofit
import com.geeks.ulul.remote.apiService.AuthenticationApiService
import com.geeks.ulul.remote.apiService.RefreshAccessTokenApiService
import com.geeks.ulul.remote.interceptor.AuthenticationInterceptor
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkClient @Inject constructor(
    private val authenticationInterceptor: AuthenticationInterceptor,
    private val authenticator: Authenticator
) {

    private val retrofit =
        provideRetrofit(
            provideOkHttpClientBuilder().apply {
                addInterceptor(authenticationInterceptor)
                authenticator(authenticator)
            }.build()
        )

    class AuthenticationNetworkClient @Inject constructor() {
        private val retrofitNoAuth =
            provideRetrofit(provideOkHttpClientBuilder().build())

        fun generateAuthenticationApiService() =
            retrofitNoAuth.createAnApi<AuthenticationApiService>()

        fun generateRefreshAccessTokenApiService() =
            retrofitNoAuth.createAnApi<RefreshAccessTokenApiService>()
    }
}

inline fun <reified T : Any> Retrofit.createAnApi(): T = create(T::class.java)