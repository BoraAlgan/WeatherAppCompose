package com.example.weatherappcompose.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.weatherappcompose.data.locale.SavedLocations
import com.example.weatherappcompose.ui.components.core.AlertDialogAddLocation
import com.example.weatherappcompose.ui.components.core.LocationCardView

@Composable
fun LocationScreen(
    locationScreenViewModel: LocationScreenViewModel,
    locationSaveState: MutableState<Boolean>,
    navController: NavHostController,
    switchState: MutableState<Boolean>
) {


    val lifecycleOwner = LocalLifecycleOwner.current
    val locationResponseState = locationScreenViewModel.mystate.collectAsStateWithLifecycle()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                locationScreenViewModel.getAllLocations()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(locationResponseState.value) {

            LocationCardView(
                data = it,
                onItemClick = { navigateLocationName ->

                    switchState.value = false
                    locationScreenViewModel.putPreferences(navigateLocationName)
                    navController.navigateUp()

                },
                onDeleteClick = { location ->
                    locationScreenViewModel.deleteLocation(location)
                }

            )

        }
    }

    if (locationSaveState.value) {
        AlertDialogAddLocation(
            onDismissRequest = { locationSaveState.value = false },
            onConfirmation = {
                val savedLocations = SavedLocations(savedLocationName = it)
                locationScreenViewModel.insertUseCase(savedLocations)
                locationSaveState.value = false
            },
            dialogTitle = "Deneme",
            dialogText = "Lütfen buraya girin",
            icon = Icons.Default.Info
        )
    }


}

