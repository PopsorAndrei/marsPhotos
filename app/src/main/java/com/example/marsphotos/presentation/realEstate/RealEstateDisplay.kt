package com.example.marsphotos.presentation.realEstate


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marsphotos.data.RealEstateRetrofitService
import com.example.marsphotos.domain.GetRealEstatePhotosImplementantion

@Composable
fun RealEstateDisplay(
    realEstateId: String?,
) {
    val viewModelRealEstate = remember {  RealEstateViewModel(
        realEstateId!!,
        GetRealEstatePhotosImplementantion(
            RealEstateRetrofitService
        )
    )}

    val stateRealEstate = viewModelRealEstate.state.collectAsStateWithLifecycle().value

    Card(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(Color.Gray)
            .padding(start = 25.dp, top = 50.dp, end = 25.dp, bottom = 50.dp)
    ) {
        if (stateRealEstate.price !=1){
            Row {
                Text(text = stateRealEstate.id)
                Spacer(modifier = Modifier.height(10.dp))
                Divider()
            }
            Row {
                Text(text = stateRealEstate.price.toString())
                Spacer(modifier = Modifier.height(10.dp))
                Divider(thickness =2.dp)
            }
            Row {
                Text(text = stateRealEstate.img_src)
                Spacer(modifier = Modifier.height(10.dp))
                Divider(thickness = 1.dp)

            }
        } else {
            Row {
                Text(text = "The data is loading", modifier = Modifier.padding(top = 50.dp))
            }
        }
       

    }
}