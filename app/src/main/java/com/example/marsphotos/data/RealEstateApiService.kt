package com.example.marsphotos.data

val RealEstateRetrofitService: RealEstateDataSource by lazy {
    retrofit.create(RealEstateDataSource::class.java)
}