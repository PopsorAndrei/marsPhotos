package com.example.marsphotos.data

val MarsPhotosRetrofitService: MarsPhotosDataSource by lazy {
    retrofit.create(MarsPhotosDataSource::class.java)
}
