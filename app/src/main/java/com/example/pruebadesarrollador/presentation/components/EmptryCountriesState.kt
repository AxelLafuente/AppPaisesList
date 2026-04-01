package com.example.pruebadesarrollador.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pruebadesarrollador.presentation.CountryScreen.CountryListFilter

@Composable
fun EmptyCountriesState(
    selectedFilter: CountryListFilter,
    query: String
) {
    val message = when {
        query.isNotBlank() -> "Nenhum país encontrado para essa busca."
        selectedFilter == CountryListFilter.FAVORITES -> "Você ainda não marcou nenhum país como favorito."
        else -> "Nenhum país disponível."
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}