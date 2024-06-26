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

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.marsphotos.presentation.Screens
import com.example.marsphotos.presentation.core.HandleEffects
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavController
) {

    val viewModel = koinViewModel<MainViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    MainScreenContent(
        state = state,
        setAction = viewModel::setAction
    )

    HandleEffects(effects = viewModel.effect) {
        handleEffect(it, navController)
    }
}

private fun handleEffect(
    effect: MainContract.MainEffect,
    navController: NavController,
) {
    when (effect) {
        is MainContract.MainEffect.NavigateToRealEstateScreen -> {
            navController.navigate(Screens.RealEstateScreen.route + "/${effect.id}")
        }
    }
}
