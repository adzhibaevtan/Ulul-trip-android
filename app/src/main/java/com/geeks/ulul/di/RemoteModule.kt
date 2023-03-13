package com.geeks.ulul.di

import com.geeks.ulul.data.remote.NetworkClient
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
    fun generateUserApiService(networkClient: NetworkClient) =
        networkClient.generateUserApiService()

    @Singleton
    @Provides
    fun generatePagingApiService(networkClient: NetworkClient) =
        networkClient.generatePagingApiService()

    @Singleton
    @Provides
    fun generateToursApiService(networkClient: NetworkClient) =
        networkClient.generateToursApiService()
}