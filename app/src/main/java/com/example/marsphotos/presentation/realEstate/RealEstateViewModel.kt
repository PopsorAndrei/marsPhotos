package com.example.marsphotos.presentation.realEstate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.data.RealEstateEntity
import com.example.marsphotos.domain.GetRealEstatePhotos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RealEstateViewModel(
    photoId: String,
    private val getPhotosService: GetRealEstatePhotos
) : ViewModel() {

    private val _realEstateUIState: MutableStateFlow<RealEstateEntity> = MutableStateFlow(
        RealEstateEntity("basic id", 1, "image_url")
    )
    val state: StateFlow<RealEstateEntity> = _realEstateUIState.asStateFlow()

    init {
        getRealEstate(photoId)
    }

    private fun getRealEstate(photoId: String) {
        viewModelScope.launch {
            val response = getPhotosService.getRealEstate(photoId)

            if (response != null) {
                _realEstateUIState.value = _realEstateUIState.value.copy(
                    id = response.id,
                    price = response.price,
                    img_src = response.img_src
                )
            }
        }
    }
}

