package com.example.weatherappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.locale.SavedLocations

@Composable
fun LocationCardView(
    data: SavedLocations,
    onItemClick: (String) -> Unit,
    onDeleteClick: (SavedLocations) -> Unit,

    ) {

    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(0.5f))
                .padding(10.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.savedLocationName,
                modifier = Modifier.clickable {
                    onItemClick.invoke(data.savedLocationName)
                },
            )

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onDeleteClick.invoke(data)
                    },
                painter = painterResource(id = R.drawable.ic_location_delete),
                contentDescription = "IconDelete"
            )
        }
    }
}