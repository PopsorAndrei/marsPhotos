package com.example.marsphotos.presentation.realEstate

import com.example.marsphotos.presentation.core.UiState
import com.example.marsphotos.presentation.core.ViewAction
import com.example.marsphotos.presentation.core.ViewSideEffect
import com.example.marsphotos.presentation.main.MainContract

interface RealEstateContract {

    sealed class RealEstateAction : ViewAction {
        data class buttonClicked(val photoUrl: String) : RealEstateAction()
    }

    data class RealEstateUiState(
        val id: String = "",
        val price: Int = 0,
        val img_src: String = ""
    ) : UiState

    sealed class RealEstateEffect : ViewSideEffect {
        data class NavigateToPhotoUrl(val photoUrl: String) : RealEstateEffect()
    }

}