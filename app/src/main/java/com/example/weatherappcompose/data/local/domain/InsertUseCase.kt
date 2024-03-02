package com.example.weatherappcompose.data.locale.domain

import com.example.weatherappcompose.data.WeatherLocalRepository
import com.example.weatherappcompose.data.locale.SavedLocations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class InsertUseCase @Inject constructor(

    private val repository: WeatherLocalRepository
) {

    fun insertData(
        locations: SavedLocations,
        onFailure: (String) -> Unit,
        onSuccess: () -> Unit
    ): Flow<Boolean> {

        return repository.insertLocation(locations)
            .flowOn(Dispatchers.IO)
            .catch {
                onFailure(it.message.toString())
            }
            .onEach {
                onSuccess()
            }
    }



}