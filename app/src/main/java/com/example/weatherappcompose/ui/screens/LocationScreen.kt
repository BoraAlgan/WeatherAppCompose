package com.example.weatherappcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LocationScreen(locationScreenViewModel: LocationScreenViewModel) {


    val lifecycleOwner = LocalLifecycleOwner.current
    val locationResponseState = locationScreenViewModel.mystate.collectAsStateWithLifecycle()


}