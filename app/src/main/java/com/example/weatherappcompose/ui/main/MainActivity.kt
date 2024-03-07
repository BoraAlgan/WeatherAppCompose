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

            val toolbarTitleState = remember { mutableStateOf("WeatherScreen") }


            Scaffold(
                topBar = {
                    ToolbarComponent(controller = controller, title = toolbarTitleState.value)
                },
                bottomBar = { NavigationComponent(navController = controller) }) {


                NavHost(startDestination = "weatherScreen", modifier = Modifier.padding(it), navController = controller) {

                    composable(
                        route = "weatherScreen",
                    ) {
                        val viewModel: WeatherScreenViewModel = hiltViewModel()
                        toolbarTitleState.value = "Weather"
                        WeatherScreen(viewModel)
                    }
                    composable(
                        route = "locationScreen",
                    ) {
                        val viewModel: LocationScreenViewModel = hiltViewModel()
                        toolbarTitleState.value = "Locations"
                        LocationScreen(viewModel)
                    }


                }

            }

        }
    }
}


