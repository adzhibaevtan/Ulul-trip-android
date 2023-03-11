package com.geeks.ulul.data.remote


import com.geeks.ulul.data.remote.apiService.auth.AuthenticationApiService
import com.geeks.ulul.data.remote.apiService.auth.RefreshAccessTokenApiService
import com.geeks.ulul.data.remote.interceptor.AuthenticationInterceptor
import com.geeks.ulul.data.remote.NetworkFastBuilder.Companion.provideOkHttpClientBuilder
import com.geeks.ulul.data.remote.NetworkFastBuilder.Companion.provideRetrofit
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