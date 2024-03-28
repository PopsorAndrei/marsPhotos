package com.example.marsphotos.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marsphotos.data.RealEstateRetrofitService
import com.example.marsphotos.domain.GetRealEstatePhotosImplementantion
import com.example.marsphotos.presentation.main.MainScreen
import com.example.marsphotos.presentation.realEstate.RealEstateDisplay
import com.example.marsphotos.presentation.realEstate.RealEstateViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(
                navigateToRealEstate = {
                    navController.navigate(route = Screens.RealEstateScreen.route + "/$it")
                }
            )
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
            val realEstateViewModel = RealEstateViewModel(
                entry.arguments?.getString("RealEstateId")!!,
                GetRealEstatePhotosImplementantion(
                    RealEstateRetrofitService
                )
            )

            RealEstateDisplay(
                realEstateViewModel.selectedRealEstate.value,
                realEstateId = entry.arguments?.getString("RealEstateId")
            )
        }
    }
}

sealed class Screens(val route: String) {
    object MainScreen : Screens("MainScreen")
    object RealEstateScreen : Screens("RealEstate")
}