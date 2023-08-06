package com.autotrade.common.di

import com.autotrade.common.carcommunication.CarCommunicationRepository
import com.autotrade.common.carcommunication.CarCommunicationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface CommonModule {
    @Binds
    fun bindCarCommunicationRepository(carCommunicationRepositoryImpl: CarCommunicationRepositoryImpl): CarCommunicationRepository
}