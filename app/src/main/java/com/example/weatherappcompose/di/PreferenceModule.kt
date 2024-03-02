package com.example.weatherappcompose.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Singleton
    @Provides
    fun preferenceProvider(@ApplicationContext context: Context): SharedPreferences {

        return context.getSharedPreferences("bora", MODE_PRIVATE)
    }
}