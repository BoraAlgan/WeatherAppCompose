package com.example.weatherappcompose.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherScreenViewModel: ViewModel() {

    private val _myState = MutableStateFlow("DENEME")
    val mystate: StateFlow<String> = _myState


}