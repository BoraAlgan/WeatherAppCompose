package com.example.weatherappcompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherappcompose.ui.components.NavigationComponent
import com.example.weatherappcompose.ui.components.ToolbarComponent
import com.example.weatherappcompose.ui.screens.LocationScreen
import com.example.weatherappcompose.ui.screens.LocationScreenViewModel
import com.example.weatherappcompose.ui.screens.WeatherScreen
import com.example.weatherappcompose.ui.screens.WeatherScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val controller = rememberNavController()

            val navBackStackEntry = controller.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            val toolbarTitleState = remember { mutableStateOf("WeatherScreen") }

            val locationSaveDialogState = remember { mutableStateOf(false) }
            val weatherShowDialogState = remember { mutableStateOf(false) }


            Scaffold(
                topBar = {
                    ToolbarComponent(
                        controller = controller,
                        title = toolbarTitleState.value,
                        onActionClick = {
                            when (currentRoute) {
                                "weatherScreen" -> {
                                    weatherShowDialogState.value = true
                                }

                                "locationScreen" -> {
                                    locationSaveDialogState.value = true
                                }
                            }
                        })
                },
                bottomBar = { NavigationComponent(navController = controller) }) {


                NavHost(startDestination = "weatherScreen", modifier = Modifier.padding(it), navController = controller) {

                    composable(
                        route = "weatherScreen",
                    ) {
                        val viewModel: WeatherScreenViewModel = hiltViewModel()
                        toolbarTitleState.value = "Weather"
                        WeatherScreen(viewModel, weatherShowDialogState)
                    }
                    composable(
                        route = "locationScreen",
                    ) {
                        val viewModel: LocationScreenViewModel = hiltViewModel()
                        toolbarTitleState.value = "Locations"
                        LocationScreen(viewModel, locationSaveDialogState, controller)
                    }
                }
            }
        }
    }
}


