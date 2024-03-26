package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"


val gsonBuilder = GsonBuilder().apply {
    setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
    .baseUrl(BASE_URL)
    .build()


interface MarsApiService {
    @GET("photos")  //  this is the path
    suspend fun getPhotos(): List<MarsPhoto>
}

object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}