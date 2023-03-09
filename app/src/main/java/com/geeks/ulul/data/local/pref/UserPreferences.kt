package com.geeks.ulul.data.local.pref

import android.content.SharedPreferences
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    var isAuthenticated: Boolean
        get() = sharedPreferences.getBoolean(PreferencesKeys.IS_AUTHENTICATED, false)
        set(value) = sharedPreferences.put(PreferencesKeys.IS_AUTHENTICATED, value)

    var accessToken: String?
        get() = sharedPreferences.getString(PreferencesKeys.ACCESS_TOKEN, "")
        set(value) = sharedPreferences.put(PreferencesKeys.ACCESS_TOKEN, value.toString())

    var refreshToken: String?
        get() = sharedPreferences.getString(PreferencesKeys.REFRESH_TOKEN, "")
        set(value) = sharedPreferences.put(PreferencesKeys.REFRESH_TOKEN, value.toString())
}