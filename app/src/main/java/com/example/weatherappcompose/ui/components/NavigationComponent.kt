package com.example.weatherappcompose.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherappcompose.R

@Composable
fun NavigationComponent(
    navController: NavHostController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val list = listOf(
        BottomNavigationItem(
            route = "weatherScreen",
            title = "Weather",
            icon = R.drawable.sunrise
        ),
        BottomNavigationItem(
            route = "locationScreen",
            title = "Location",
            icon = R.drawable.ic_add_menu_location
        )
    )

    NavigationBar {

        list.forEach { item ->

            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "",
                        modifier = Modifier.size(if (currentRoute == item.route) 26.dp else 22.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontWeight = if (currentRoute == item.route) FontWeight.Bold else FontWeight.Normal,
                        fontSize = if (currentRoute == item.route) 16.sp else 12.sp
                    )
                }
            )

        }
    }


}

data class BottomNavigationItem(
    val route: String,
    val title: String,
    val icon: Int
)