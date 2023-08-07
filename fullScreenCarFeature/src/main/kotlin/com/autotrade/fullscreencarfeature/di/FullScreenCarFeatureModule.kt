package com.autotrade.fullscreencarfeature.di

import com.autotrade.di.scopes.FragmentScope
import com.autotrade.fullscreencarfeature.domain.CarsRepository
import com.autotrade.fullscreencarfeature.domain.CarsRepositoryImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
interface FullScreenCarFeatureModule {
    @Binds
    fun bindsCarRepository(carsRepositoryImpl: CarsRepositoryImpl): CarsRepository

    companion object {
        @Provides
        fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)


        @FragmentScope
        @Provides
        fun provideCarCollectionRef(): CollectionReference = Firebase.firestore.collection("auto")
    }
}