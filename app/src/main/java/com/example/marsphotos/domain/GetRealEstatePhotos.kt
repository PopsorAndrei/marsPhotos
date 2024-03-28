package com.example.marsphotos.domain

import com.example.marsphotos.data.MarsPhotosDataSource
import com.example.marsphotos.data.RealEstateDataSource
import com.example.marsphotos.data.RealEstateEntity


interface GetRealEstatePhotos {
    suspend fun getRealEstate(realEstateId: String) : RealEstateEntity?
}
class GetRealEstatePhotosImplementantion (
    private val marsPhotosDataSource: RealEstateDataSource
) :GetRealEstatePhotos{
    override suspend fun getRealEstate(realEstateId:String ): RealEstateEntity? = marsPhotosDataSource.getRealEstate().find { it.id == realEstateId }
}