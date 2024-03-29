package com.example.marsphotos.domain

import com.example.marsphotos.data.MarsPhotosDataSource

interface GetPhotos {
    suspend fun getPhotos(): List<MarsPhoto>

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
}
