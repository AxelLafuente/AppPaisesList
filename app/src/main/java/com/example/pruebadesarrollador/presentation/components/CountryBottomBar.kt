package com.example.pruebadesarrollador.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.pruebadesarrollador.presentation.CountryScreen.CountryListFilter

@Composable
fun CountryBottomBar(
    selectedFilter: CountryListFilter,
    onFilterSelected: (CountryListFilter) -> Unit
) {
    NavigationBar(
        containerColor = Color.White
    ) {

        NavigationBarItem(
            selected = selectedFilter == CountryListFilter.FAVORITES,
            onClick = { onFilterSelected(CountryListFilter.FAVORITES) },

            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorite countries"
                )
            },

            label = {
                Text("Overview")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF1565C0),
                selectedTextColor = Color(0xFF1565C0),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = selectedFilter == CountryListFilter.ALL,
            onClick = { onFilterSelected(CountryListFilter.ALL) },

            icon = {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = "All countries"
                )
            },

            label = {
                Text("Maps")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF1565C0),
                selectedTextColor = Color(0xFF1565C0),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}