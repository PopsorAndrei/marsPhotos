package com.example.marsphotos.presentation.realEstate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.marsphotos.data.RealEstateEntity

@Composable
fun RealEstateDisplay(
    state: RealEstateEntity?,
    realEstateId: String?
) {
    //val state = realEstateViewModel.selectedRealEstate

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(Color.Green)
            .padding(horizontal = 10.dp)
    ) {
        Text(text = "random display text")
        Row {
            state?.let { Text(text = it.id) }
        }

    }
}