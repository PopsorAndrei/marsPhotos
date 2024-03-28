package com.example.marsphotos.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marsphotos.data.RealEstateEntity
import com.example.marsphotos.data.RealEstateRetrofitService
import com.example.marsphotos.domain.GetRealEstatePhotosImplementantion

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MainScreen.route){
        composable(route = Screens.MainScreen.route){
            MarsPhotosApp(navController)
        }

        composable(route = "test") {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)){
                Text(text = "nav test")
            }
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
            val realEstateViewModel = RealEstateViewModel(entry.arguments?.getString("RealEstateId")!!,
                GetRealEstatePhotosImplementantion(
                    RealEstateRetrofitService
                )
            )

            RealEstateDisplay(realEstateViewModel.selectedRealEstate.value,realEstateId = entry.arguments?.getString("RealEstateId"))
        }
    }
}

sealed class Screens(val route:String){
    object MainScreen :Screens("MainScreen")
    object RealEstateScreen :Screens("RealEstate")
}