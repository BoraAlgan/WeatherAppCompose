package com.example.weatherappcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.data.remote.domain.WeatherUseCase
import com.example.weatherappcompose.data.remote.model.WeatherResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @Inject constructor(

    private val useCase: WeatherUseCase,



    ) : ViewModel() {

    private val _myState = MutableStateFlow(WeatherResponseModel())
    val mystate: StateFlow<WeatherResponseModel> = _myState.asStateFlow()


    fun fetchWeatherData(query: String) {

        useCase.fetchWeatherData(
            query,
            onSuccess = {
                _myState.value = it
            },
            onFailure = {
//                toastMessage.value = it
            }
        )
            .launchIn(viewModelScope)
    }

    fun fetchWeatherWithCordData(lat: Double, lon: Double,locationName: String) {

        useCase.fetchWeatherWithCordData(
            lat,
            lon,
            onSuccess = {
                it.name = locationName
                _myState.value = it
            },
            onFailure = {
                println("HATA $it")
            }
        )
            .launchIn(viewModelScope)
    }


    }