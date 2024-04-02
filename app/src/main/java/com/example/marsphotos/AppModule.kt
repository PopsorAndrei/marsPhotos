package com.example.marsphotos

import com.example.marsphotos.data.MarsPhotosDataSource
import com.example.marsphotos.data.RealEstateDataSource
import com.example.marsphotos.domain.GetPhotos
import com.example.marsphotos.domain.GetPhotosImpl
import com.example.marsphotos.domain.GetRealEstatePhotos
import com.example.marsphotos.domain.GetRealEstatePhotosImplementantion
import com.example.marsphotos.presentation.main.MainViewModel
import com.example.marsphotos.presentation.realEstate.RealEstateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<GetRealEstatePhotos> { GetRealEstatePhotosImplementantion(get()) }

    single<GetPhotos> { GetPhotosImpl(get()) }

    single<RealEstateDataSource> { com.example.marsphotos.data.retrofit.create(RealEstateDataSource::class.java) }

    single<MarsPhotosDataSource> { com.example.marsphotos.data.retrofit.create(MarsPhotosDataSource::class.java) }

    viewModel { MainViewModel(get()) }

    viewModel { (id: String) ->
        RealEstateViewModel(id, get())
    }


}