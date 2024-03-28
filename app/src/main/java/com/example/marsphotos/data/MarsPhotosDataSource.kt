package com.example.marsphotos.data

import retrofit2.http.GET

interface MarsPhotosDataSource {
    @GET("photos")  //  this is the path
    suspend fun getPhotos(): List<MarsPhotoEntity>


}
