@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.weatherappcompose.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.ToolbarColor

@Composable
fun ToolbarComponent(
    title: String,
    controller: NavHostController,
    onActionClick: () -> (Unit)

) {

    val navBackStackEntry = controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(4.dp),
        title = {
            Text(text = title)
        },
        navigationIcon = {

        },
        actions = {
            IconButton(
                onClick = {
                    onActionClick()
                }
            ) {
                when (currentRoute) {
                    "weatherScreen" -> {
                        Icon(painter = painterResource(id = R.drawable.ic_edit_location), contentDescription = "ShowLocation", tint = Color.White)
                    }

                    "locationScreen" -> {
                        Icon(painter = painterResource(id = R.drawable.ic_location_add), contentDescription = "AddLocation", tint = Color.White)
                    }

                }

            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ToolbarColor,
            titleContentColor = Color.White
        )
    )

}