package com.geeks.ulul.repository

import com.geeks.ulul.data.base.BaseRepository
import com.geeks.ulul.data.local.pref.UserPreferences
import com.geeks.ulul.data.remote.apiService.user.UserApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApiService: UserApiService,
    private val userPreferences: UserPreferences
) : BaseRepository() {

    fun deleteAccount() = makeNetworkRequest {
        userApiService.deleteAccount(
            userPreferences.userID.toString().toInt()
        )
    }

    fun getFavoriteTours() = makeNetworkRequest {
        userApiService.fetchFavorites(userPreferences.userID.toString().toInt()).favoriteTour
    }
}