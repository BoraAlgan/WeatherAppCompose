package com.example.weatherappcompose.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getString
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.BackGroundColorEnd
import com.example.weatherappcompose.ui.theme.BackGroundColorImage
import com.example.weatherappcompose.ui.theme.BackGroundColorStart
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import java.util.Locale
import kotlin.math.roundToInt

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(weatherScreenViewModel: WeatherScreenViewModel) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val weatherResponseState = weatherScreenViewModel.mystate.collectAsStateWithLifecycle()
    val finePermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val coarsePermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_COARSE_LOCATION)

    val locationPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
        //mapdeki herhangi bir izin verilmediyse if bloğu, verildi ise else bloğu dönecek
        if (isGranted.containsValue(false)) {

        } else {
            startLocationUpdates(context) { lat, lon, locationName ->
                weatherScreenViewModel.fetchWeatherWithCordData(lat, lon, locationName)
            }
        }

    }

    LaunchedEffect(finePermissionState, coarsePermissionState) {
        when {
            finePermissionState.status.isGranted && coarsePermissionState.status.isGranted -> {
                startLocationUpdates(context) { lat, lon, locationName ->
                    weatherScreenViewModel.fetchWeatherWithCordData(lat, lon, locationName)
                }
            }

            finePermissionState.status.shouldShowRationale && coarsePermissionState.status.shouldShowRationale -> {
                //dialog basılacak
            }

            else -> {
                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {

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
            )
            .padding(vertical = 8.dp, horizontal = 16.dp,),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(weatherResponseState.value.name.toString(), fontSize = 30.sp, color = Color.White)
            Text("Updated at: 11.02.2024 19.47 PM", color = Color.White)
        }

        Column() {
            Column(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val celcTemp = stringResource(id = R.string.celcius_temp, weatherResponseState.value.main?.temp?.roundToInt().toString())
                Text(celcTemp, color = Color.White, fontSize = 70.sp, modifier = Modifier.padding(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val minTemp = stringResource(id = R.string.min_temp, weatherResponseState.value.main?.tempMin?.roundToInt().toString())
                    Text(minTemp,color = Color.White)
                    val maxTemp = stringResource(id = R.string.max_temp, weatherResponseState.value.main?.tempMax?.roundToInt().toString())
                    Text(maxTemp, color = Color.White)
                }
            }


            Row(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .width(112.dp)
                        .background(BackGroundColorImage)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.sunrise),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = ""
                    )
                    Text("Sunrise", color = Color.White)
                    Text("5.30", color = Color.White)

                }
                Column(
                    modifier = Modifier
                        .width(112.dp)
                        .background(BackGroundColorImage)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally


                ) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.sunset),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = ""
                    )
                    Text("Sunset", color = Color.White)
                    Text("5.30", color = Color.White)

                }
                Column(
                    modifier = Modifier
                        .width(112.dp)
                        .background(BackGroundColorImage)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally


                ) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.wind),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text("Wind", color = Color.White)
                    Text("5.30", color = Color.White)

                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .width(112.dp)
                        .background(BackGroundColorImage)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.pressure),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = ""
                    )
                    Text("Pressure", color = Color.White)
                    Text("5.30", color = Color.White)

                }
                Column(
                    modifier = Modifier
                        .width(112.dp)
                        .background(BackGroundColorImage)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.humidity),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = ""
                    )
                    Text("Pressure", color = Color.White)
                    Text("5.30", color = Color.White)
                }
                Column(
                    modifier = Modifier
                        .width(112.dp)
                        .background(BackGroundColorImage)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sea_level),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = "",
                        modifier = Modifier.size(25.dp)
                    )
                    Text("Pressure", color = Color.White)
                    Text("5.30", color = Color.White)
                }
            }
        }

    }
}


private fun isLocationAvailable(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}

@SuppressLint("MissingPermission")
fun startLocationUpdates(
    context: Context,
    locationRecieved: (lat: Double, lon: Double, locationName: String) -> Unit
) {

    if (isLocationAvailable(context)) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        val locationTask: Task<Location> = fusedLocationClient.lastLocation

        locationTask

            .addOnSuccessListener { location ->
                Toast.makeText(context, "Location obtained :).", Toast.LENGTH_SHORT).show()

                val latitude = location.latitude
                val longitude = location.longitude
                println("" + latitude + "" + longitude)




                if (Geocoder.isPresent()) {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses: List<Address>? =
                        geocoder.getFromLocation(latitude, longitude, 1)

                    if (!addresses.isNullOrEmpty()) {
                        val city = addresses[0].adminArea
                        val country = addresses[0].countryName
                        val postalCode = addresses[0].postalCode


                        locationRecieved(
                            latitude,
                            longitude,
                            city
                        )

                        println(city + country + postalCode)
                    }
                } else {
                    Toast.makeText(context, "Unknown location.", Toast.LENGTH_SHORT).show()

                    //last ( geocoder datasına ulaşılamadığı zaman)
                }

            }

            .addOnFailureListener { exception ->
                //lat lon değerini alamadığımız case
                Toast.makeText(
                    context,
                    "Location could not be obtained. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()

            }

    } else {
        Toast.makeText(context, "Turn on location.", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        context.startActivity(intent)
    }


}



