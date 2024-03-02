package com.example.weatherappcompose.data.remote.api

import com.example.weatherappcompose.data.remote.model.WeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") query: String?,
        @Query("units") units: String = "metric" // Celcius Birimi için
    ): WeatherResponseModel

    @GET("weather")
    suspend fun  getWeatherWithCord(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("units") units: String = "metric"
    ): WeatherResponseModel


   //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
   // https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
}
