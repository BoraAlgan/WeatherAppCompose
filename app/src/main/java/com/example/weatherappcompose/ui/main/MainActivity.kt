package com.example.weatherappcompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherappcompose.ui.screens.LocationScreen
import com.example.weatherappcompose.ui.screens.LocationScreenViewModel
import com.example.weatherappcompose.ui.screens.WeatherScreen
import com.example.weatherappcompose.ui.screens.WeatherScreenViewModel
import com.example.weatherappcompose.ui.theme.WeatherAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val controller = rememberNavController()



            Scaffold(topBar = {}, bottomBar = {}) {

                NavHost(startDestination = "weatherScreen", modifier = Modifier.padding(it), navController = controller) {

                    composable(
                        route = "weatherScreen"
                    ){
                        val viewModel: WeatherScreenViewModel = hiltViewModel()
                        WeatherScreen(viewModel)
                    }
                    composable(
                        route = "locationScreen"
                    ){
                        val viewModel: LocationScreenViewModel = hiltViewModel()
                        LocationScreen(viewModel)
                    }


                }

            }

        }
    }
}


