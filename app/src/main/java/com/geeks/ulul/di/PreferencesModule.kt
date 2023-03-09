package com.geeks.ulul.di

import android.content.Context
import android.content.SharedPreferences
import com.geeks.ulul.data.local.pref.PreferencesKeys.ULUL_TRIP_SHARED_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun generateSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            ULUL_TRIP_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
}