package com.geeks.ulul.data.remote


import com.geeks.ulul.data.remote.NetworkFastBuilder.Companion.provideOkHttpClientBuilder
import com.geeks.ulul.data.remote.NetworkFastBuilder.Companion.provideRetrofit
import com.geeks.ulul.data.remote.apiService.auth.AuthenticationApiService
import com.geeks.ulul.data.remote.apiService.auth.RefreshAccessTokenApiService
import com.geeks.ulul.data.remote.apiService.tours.PagingApiService
import com.geeks.ulul.data.remote.apiService.tours.ToursApiService
import com.geeks.ulul.data.remote.apiService.user.UserApiService
import com.geeks.ulul.data.remote.interceptor.AuthenticationInterceptor
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

    fun generatePagingApiService() = retrofit.createAnApi<PagingApiService>()

    fun generateUserApiService() = retrofit.createAnApi<UserApiService>()

    fun generateToursApiService() = retrofit.createAnApi<ToursApiService>()


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