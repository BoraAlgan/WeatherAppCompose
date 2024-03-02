package com.example.weatherappcompose.data

import android.content.Context
import androidx.room.Room
import com.example.weatherappcompose.data.locale.LocationDAO
import com.example.weatherappcompose.data.locale.LocationDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideLocationDatabase(@ApplicationContext context: Context): LocationDataBase {

        return Room.databaseBuilder(
            context,
            LocationDataBase::class.java,
            "location.db"
        ).build()


    }

    @Singleton
    @Provides
    fun provideLocationDao(database: LocationDataBase): LocationDAO {

        return database.locationDao()

    }

}