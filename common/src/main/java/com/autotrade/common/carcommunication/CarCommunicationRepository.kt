package com.autotrade.common.carcommunication

interface CarCommunicationRepository {
    suspend fun setCarDomain(carDomain: CarDomain)

    suspend fun getCarDomain(): CarDomain?

    suspend fun clearCarDomain()
}