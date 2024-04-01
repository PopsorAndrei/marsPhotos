package com.example.marsphotos.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.example.marsphotos.domain.MarsPhoto
import com.example.marsphotos.presentation.ErrorScreen
import com.example.marsphotos.presentation.LoadingScreen
import com.example.marsphotos.presentation.MarsTopAppBar
import com.example.marsphotos.presentation.ResultScreen
import com.example.marsphotos.ui.theme.MarsPhotosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    state: MainContract.MainUiState,
    setAction: (MainContract.MainAction) -> Unit

) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MarsTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val mainModifier = Modifier.padding(it)

            when {
                state.isLoading -> LoadingScreen(modifier = mainModifier.fillMaxWidth())
                state.hasError -> ErrorScreen(modifier = mainModifier.fillMaxSize())

                else -> {
                    ResultScreen(
                        photos = state.photos,
                        setAction = setAction,
                        modifier = mainModifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenContentPreview() {
    MarsPhotosTheme {
        MainScreenContent(
            state = MainContract.MainUiState(
                photos = listOf(MarsPhoto(id = "123", imageUrl = "url"))
            ),
            setAction = {}
        )
    }
}