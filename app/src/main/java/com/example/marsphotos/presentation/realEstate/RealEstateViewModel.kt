package com.example.marsphotos.presentation.realEstate

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.data.RealEstateEntity
import com.example.marsphotos.domain.GetPhotos
import com.example.marsphotos.domain.GetRealEstatePhotos
import kotlinx.coroutines.launch

class RealEstateViewModel(
    private val photoId: String,
    private val getPhotosService: GetRealEstatePhotos
): ViewModel(){
    val selectedRealEstate: MutableState<RealEstateEntity?> = mutableStateOf(RealEstateEntity("",1,""))

    init {
        Log.d("Andrei", photoId)
        getRealEstate(photoId)
    }

    private fun getRealEstate(photoId: String) {
        viewModelScope.launch {
            Log.d("TEST","TEST")
            selectedRealEstate.value = getPhotosService.getRealEstate(photoId)
            selectedRealEstate.value?.let { Log.d("TEST", it.img_src) }

            //Log.d("TESTTTT", selectedRealEstate.value.toString())

        }
    }
}

