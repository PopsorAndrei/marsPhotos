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

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.domain.GetPhotos
import com.example.marsphotos.domain.MarsPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MarsViewModel(
    private val getPhotos: GetPhotos
) : ViewModel() {
    private val _marsUiState: MutableStateFlow<MarsUiState> = MutableStateFlow(MarsUiState())
    val marsUiState: StateFlow<MarsUiState> = _marsUiState.asStateFlow()

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            val photos = getPhotos.getPhotos()

            try {
                _marsUiState.value = _marsUiState.value.copy(
                    photos = photos,
                    isLoading = false
                )
            } catch (e: IllegalAccessException) {
                _marsUiState.value = _marsUiState.value.copy(
                    hasError = true,
                    isLoading = false
                )
            }

            Log.i("ciprian22", "state: ${marsUiState.value}")
        }
    }
}

data class MarsUiState(
    val photos: List<MarsPhoto> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)