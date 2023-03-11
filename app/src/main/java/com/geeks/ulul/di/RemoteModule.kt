package com.geeks.ulul.di

import com.geeks.ulul.data.remote.NetworkClient
import com.geeks.ulul.data.remote.apiService.tours.PagingApiService
import com.geeks.ulul.data.remote.NetworkFastBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun generateAuthenticationApiService(networkClient: NetworkClient.AuthenticationNetworkClient) =
        networkClient.generateAuthenticationApiService()

    @Singleton
    @Provides
    fun generateRefreshAccessTokensApiService(networkClient: NetworkClient.AuthenticationNetworkClient) =
        networkClient.generateRefreshAccessTokenApiService()

    @Singleton
    @Provides
    fun generatePagingApiService(): PagingApiService =
        NetworkFastBuilder.provideRetrofit(NetworkFastBuilder.provideOkHttpClientBuilder().build())
            .create(PagingApiService::class.java)
}