package com.example.weatherappcompose.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherappcompose.data.locale.SavedLocations
import com.example.weatherappcompose.ui.components.LocationCardView

@Composable
fun LocationScreen(locationScreenViewModel: LocationScreenViewModel) {


    val lifecycleOwner = LocalLifecycleOwner.current
    val locationResponseState = locationScreenViewModel.mystate.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(locationResponseState.value) {

            LocationCardView(
                data = it,
                onItemClick = {},
                onDeleteClick = { location ->
                    locationScreenViewModel.deleteLocation(location)
                }
            )

        }
    }

}