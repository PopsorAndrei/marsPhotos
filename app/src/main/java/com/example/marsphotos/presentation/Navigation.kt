package com.example.marsphotos.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marsphotos.presentation.main.MainScreen
import com.example.marsphotos.presentation.realEstate.RealEstateDisplay

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screens.RealEstateScreen.route + "/{RealEstateId}",
            arguments = listOf(
                navArgument("RealEstateId")
                {
                    type = NavType.StringType
                }
            ))
        { entry ->

            RealEstateDisplay(
                realEstateId = entry.arguments?.getString("RealEstateId")
            )
        }
    }
}

sealed class Screens(val route: String) {
    object MainScreen : Screens("MainScreen")
    object RealEstateScreen : Screens("RealEstate")
}