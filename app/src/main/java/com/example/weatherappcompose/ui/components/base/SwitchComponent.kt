package com.example.weatherappcompose.ui.components.base

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun SwitchButton(
    onCheckedState: MutableState<Boolean>,
    ) {

    Switch(
        checked = onCheckedState.value,
        onCheckedChange = {
            onCheckedState.value = it

        }
    )
}