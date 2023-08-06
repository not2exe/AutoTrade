package com.autotrade.searchscreenfeature.di

import com.autotrade.searchscreenfeature.domain.CarFiltersRepository
import com.autotrade.searchscreenfeature.domain.CarFiltersRepositoryImpl
import com.autotrade.searchscreenfeature.domain.CarPagingRepository
import com.autotrade.searchscreenfeature.domain.CarPagingRepositoryImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface SearchScreenFeatureModule {
    @Binds
    fun bindCarFiltersRepository(carFiltersRepositoryImpl: CarFiltersRepositoryImpl): CarFiltersRepository

    @Binds
    fun bindCarPagingRepository(carPagingRepositoryImpl: CarPagingRepositoryImpl): CarPagingRepository

    companion object {
        @Provides
        fun provideCarCollectionRef(): CollectionReference = Firebase.firestore.collection("auto")
    }
}