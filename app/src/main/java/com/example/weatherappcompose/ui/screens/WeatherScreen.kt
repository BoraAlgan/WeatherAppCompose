package com.example.weatherappcompose.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.BackGroundColorEnd
import com.example.weatherappcompose.ui.theme.BackGroundColorStart

@Composable
fun WeatherScreen(weatherScreenViewModel: WeatherScreenViewModel) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val weatherResponseState = weatherScreenViewModel.mystate.collectAsStateWithLifecycle()


    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                weatherScreenViewModel.fetchWeatherData("İstanbul")
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        BackGroundColorStart, BackGroundColorEnd
                    )
                )
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(weatherResponseState.value.name.toString(), fontSize = 30.sp)
            Text("Updated at: 11.02.2024 19.47 PM")
        }

        Column() {
            Column(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("C", fontSize = 70.sp, modifier = Modifier.padding(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Min Temp: 13C")
                    Text("Max Temp: 13C")
                }
            }


            Row(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.width(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.sunrise),
                        contentDescription = ""
                    )
                    Text("Sunrise")
                    Text("5.30")

                }
                Column(
                    modifier = Modifier.width(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally


                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sunset),
                        contentDescription = ""
                    )
                    Text("Sunset")
                    Text("5.30")

                }
                Column(
                    modifier = Modifier.width(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally


                ) {
                    Image(painter = painterResource(id = R.drawable.wind), contentDescription = "")
                    Text("Wind")
                    Text("5.30")

                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 28.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.width(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pressure),
                        contentDescription = ""
                    )
                    Text("Pressure")
                    Text("5.30")

                }
                Column(
                    modifier = Modifier.width(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.humidity),
                        contentDescription = ""
                    )
                    Text("Pressure")
                    Text("5.30")
                }
                Column(
                    modifier = Modifier.width(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sea_level),
                        contentDescription = "",
                        modifier = Modifier.size(33.dp)
                    )
                    Text("Pressure")
                    Text("5.30")
                }
            }
        }

    }
}
