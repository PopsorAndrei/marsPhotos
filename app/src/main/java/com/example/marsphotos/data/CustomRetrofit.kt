package com.example.marsphotos.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

val gsonBuilder = GsonBuilder().apply {
    setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
}

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
    .baseUrl(BASE_URL)
    .build()