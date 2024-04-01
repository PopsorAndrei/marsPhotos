package com.example.marsphotos.presentation.main

import com.example.marsphotos.domain.MarsPhoto
import com.example.marsphotos.presentation.core.UiState
import com.example.marsphotos.presentation.core.ViewAction
import com.example.marsphotos.presentation.core.ViewSideEffect

interface MainContract {
    sealed class MainAction : ViewAction {
        data class ItemClicked(val id: String) : MainAction()
    }

    data class MainUiState(
        val photos: List<MarsPhoto> = emptyList(),
        val isLoading: Boolean = true,
        val hasError: Boolean = false
    ) : UiState

    sealed class MainEffect : ViewSideEffect {
        data class NavigateToRealEstateScreen(val id: String) : MainEffect()
    }
}