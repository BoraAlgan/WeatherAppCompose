package com.example.weatherappcompose.ui.screens

import android.content.SharedPreferences
import androidx.core.content.edit
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
class WeatherScreenViewModel @Inject constructor(

    private val useCase: WeatherUseCase,

    private val preferences: SharedPreferences


) : ViewModel() {

    private val _myState = MutableStateFlow<WeatherResponseModel?>(null)
    val mystate: StateFlow<WeatherResponseModel?> = _myState.asStateFlow()

    private val _errorState = MutableStateFlow<Boolean>(false)
    val errorState: StateFlow<Boolean> = _errorState.asStateFlow()

    fun disableErrorState() {
        _errorState.value = false
        preferences.edit {
            remove(CITY)
        }
    }


    fun fetchWeatherData(query: String) {

        useCase.fetchWeatherData(
            query,
            onSuccess = {
                _myState.value = it
            },
            onFailure = {
                _errorState.value = true
            }
        )
            .launchIn(viewModelScope)
    }

    fun fetchWeatherWithCordData(lat: Double, lon: Double, locationName: String) {

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

    fun putPreferences(value: String) {
        preferences.edit {
            putString(CITY, value)
        }
        fetchWeatherData(value)
    }

    fun checkQuery(onPreferencesNull: () -> Unit) {
        val city = getPreferences(CITY)
        if (city == null) {
            onPreferencesNull()
        } else {
            fetchWeatherData(city)
        }
    }

    private fun getPreferences(value: String): String? {
        return preferences.getString(value, null)
    }

    companion object {
        private val CITY = "city"
    }

}