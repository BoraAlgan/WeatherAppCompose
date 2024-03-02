package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.remote.api.WeatherApiClient
import com.example.weatherappcompose.data.remote.model.WeatherResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRemoteRepository @Inject constructor(
    private val serviceWeather: WeatherApiClient
){


    fun getWeather(query: String): Flow<WeatherResponseModel> {

        return flow {
            val response = serviceWeather.getWeather(query)
            emit(response)
        }

    }

    fun getWeatherWithCord(lat: Double, lon: Double): Flow<WeatherResponseModel> {

        return flow {
            val responseWithCord = serviceWeather.getWeatherWithCord(lat, lon)
            emit(responseWithCord)
        }
    }

}