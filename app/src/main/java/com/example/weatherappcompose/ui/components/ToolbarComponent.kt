@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.weatherappcompose.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherappcompose.ui.theme.ToolbarColor

@Composable
fun ToolbarComponent(
    title: String,
    controller: NavHostController
) {

    val navBackStackEntry = controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val topScreens = listOf("weatherScreen", "locationScreen")

    val openAlertDialog = remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(4.dp),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (!topScreens.contains(currentRoute)) {
                IconButton(onClick = {
                    controller.navigateUp()
                }) {
                }
            }
        },
        actions = {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = true },
                onConfirmation = {
                    openAlertDialog.value = true
                    println("Confirmation registered")
                },
                dialogTitle = "Alert dialog example",
                dialogText = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ToolbarColor,
            titleContentColor = Color.White
        )
    )

}