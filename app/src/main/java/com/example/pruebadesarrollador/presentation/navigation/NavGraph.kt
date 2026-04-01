package com.example.pruebadesarrollador.presentation.navigation

import CountryDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pruebadesarrollador.presentation.CountryScreen.CountryScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.CountryScreen.route
    ) {

        composable(Screen.CountryScreen.route) {
            CountryScreen(
                onItemClick = { country ->
                    navController.navigate(
                        Screen.CountryDetail.createRoute(country.common)
                    )
                }
            )
        }

        composable(
            route = Screen.CountryDetail.route,
            arguments = listOf(
                navArgument("countryName") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val countryName =
                backStackEntry.arguments?.getString("countryName") ?: ""

            CountryDetailScreen(countryName = countryName,
                onBackClick = {
                    navController.popBackStack()
                })
        }
    }
}