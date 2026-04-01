package com.example.pruebadesarrollador.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DriverSideItem(
    driverSide: String // "left" ou "right"
) {

    val isRight = driverSide.equals("right", ignoreCase = true)

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {

        Text(
            text = "Driver Side",
            style = MaterialTheme.typography.labelMedium,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            DriverOption(
                text = "Left",
                isSelected = !isRight
            )

            DriverOption(
                text = "Right",
                isSelected = isRight
            )
        }
    }
}
@Composable
private fun DriverOption(
    text: String,
    isSelected: Boolean
) {

    val color = if (isSelected) {
        Color.Black
    } else {
        Color.LightGray
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.DirectionsCar,
            contentDescription = null,
            tint = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            color = color,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}