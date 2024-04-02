package com.example.marsphotos.presentation.realEstate

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.marsphotos.presentation.Screens
import com.example.marsphotos.presentation.core.HandleEffects
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RealEstateDisplay(
    realEstateId: String?,
    navController: NavController
) {
    val viewModelRealEstate = koinViewModel<RealEstateViewModel> { parametersOf(realEstateId) }

    val stateRealEstate = viewModelRealEstate.uiState.collectAsStateWithLifecycle().value

    RealEstateContent(state = stateRealEstate, viewModelRealEstate::setAction)

    HandleEffects(effects = viewModelRealEstate.effect) {
        handleEffect(it, navController)
    }
}

private fun handleEffect(
    effect: RealEstateContract.RealEstateEffect,
    navController: NavController
) {
    when (effect) {
        is RealEstateContract.RealEstateEffect.NavigateToPhotoUrl -> {
            navController.navigate(Screens.PhotoUrlScreen.route + "/${effect.photoUrl}")
        }
    }
}