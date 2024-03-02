package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.locale.LocationDAO
import com.example.weatherappcompose.data.locale.SavedLocations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherLocalRepository @Inject constructor(
    private val locationDAO: LocationDAO
){




    fun insertLocation(location: SavedLocations): Flow<Boolean> {

        return flow {
            locationDAO.insert(location)
            emit(true)
        }

    }

    fun deleteLocation(location: SavedLocations): Flow<Boolean> {

        return flow {
            locationDAO.delete(location)
            emit(true)
        }

    }

    fun getAllLocations() : Flow<List<SavedLocations>> {

        return flow {
            val response = locationDAO.getAll()
            emit(response)
        }

    }

}


