package com.example.marsphotos.presentation.RealEstatePhotoDescription

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainPhotoDescription(photoUrlString :String?){

    Log.d("ANDREI", photoUrlString!!)
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize()){
        Column{
            Text(text = "ai ajuns unde trebe")
        }
        Column {
                Text(text = photoUrlString)
        }

    }
}