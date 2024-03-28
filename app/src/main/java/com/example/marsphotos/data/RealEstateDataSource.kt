package com.example.marsphotos.data

import retrofit2.http.GET

interface RealEstateDataSource {
    @GET("realestate")
    suspend fun getRealEstate():  List<RealEstateEntity>
}