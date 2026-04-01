package com.example.pruebadesarrollador.presentation.navigation

sealed class Screen(val route: String) {
    object CountryScreen : Screen("country_list")
    object CountryDetail : Screen("country_detail/{countryName}") {
        fun createRoute(countryName: String) = "country_detail/$countryName"
    }
}