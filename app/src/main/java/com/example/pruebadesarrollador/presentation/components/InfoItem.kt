package com.example.pruebadesarrollador.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InfoItem(
    title: String,
    value: String
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = Color.DarkGray
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}