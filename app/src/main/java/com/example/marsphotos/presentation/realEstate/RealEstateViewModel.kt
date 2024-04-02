package com.example.marsphotos.presentation.realEstate

import androidx.lifecycle.viewModelScope
import com.example.marsphotos.domain.GetRealEstatePhotos
import com.example.marsphotos.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class RealEstateViewModel(
    photoId: String,
    private val getPhotosService: GetRealEstatePhotos
) : BaseViewModel<RealEstateContract.RealEstateAction, RealEstateContract.RealEstateUiState, RealEstateContract.RealEstateEffect>() {

    init {
        getRealEstate(photoId)
    }

    private fun getRealEstate(photoId: String) {
        viewModelScope.launch {
            val response = getPhotosService.getRealEstate(photoId)
            if (response != null) {
                setState {
                    copy(
                        id = response.id,
                        price = response.price,
                        img_src = response.img_src
                    )
                }
            }
        }
    }

    override fun setInitialState() = RealEstateContract.RealEstateUiState()

    override fun handleViewAction(action: RealEstateContract.RealEstateAction) {
        when (action) {
            is RealEstateContract.RealEstateAction.buttonClicked -> {
                setEffect { RealEstateContract.RealEstateEffect.NavigateToPhotoUrl(action.photoUrl) }
            }
        }
    }


}

