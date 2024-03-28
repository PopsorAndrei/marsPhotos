package com.example.marsphotos.domain

import com.example.marsphotos.data.MarsPhotosDataSource
import com.example.marsphotos.data.RealEstateDataSource
import com.example.marsphotos.data.RealEstateEntity

interface GetPhotos {
    suspend fun getPhotos(): List<MarsPhoto>

    //suspend fun getRealEstate(realEstateId: String) : RealEstateEntity?
}

class GetPhotosImpl(
    private val marsPhotosDataSource: MarsPhotosDataSource
) : GetPhotos {
    override suspend fun getPhotos(): List<MarsPhoto> =
        marsPhotosDataSource.getPhotos().map {
            MarsPhoto(
                id = it.id,
                imageUrl = it.imgSrc
            )
        }

//    override suspend fun getRealEstate(realEstateId:String ): RealEstateEntity? = marsPhotosDataSource.getRealEstate().find { it.id == realEstateId }
}
