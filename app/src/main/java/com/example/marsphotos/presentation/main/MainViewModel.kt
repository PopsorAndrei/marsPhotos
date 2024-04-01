/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.marsphotos.domain.GetPhotos
import com.example.marsphotos.domain.MarsPhoto
import com.example.marsphotos.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPhotos: GetPhotos
) : BaseViewModel<MainContract.MainAction, MainContract.MainUiState, MainContract.MainEffect>() {

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            val photos = getPhotos.getPhotos()

            try {
                setState { copy(photos = photos, isLoading = false) }
            } catch (e: IllegalAccessException) {
                setState { copy(hasError = true, isLoading = false) }
            }

        }
    }

    override fun setInitialState() = MainContract.MainUiState()

    override fun handleViewAction(action: MainContract.MainAction) {
        when (action) {
            is MainContract.MainAction.ItemClicked -> {
                setEffect { MainContract.MainEffect.NavigateToRealEstateScreen(action.id) }
            }
        }
    }
}

data class MarsUiState(
    val photos: List<MarsPhoto> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)