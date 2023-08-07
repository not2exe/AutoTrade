package com.autotrade.common.carcommunication

import javax.inject.Inject

class CarCommunicationRepositoryImpl @Inject constructor(
    private val communicationStore: CarCommunicationStore
) : CarCommunicationRepository {

    override suspend fun setCarDomain(carDomain: CarDomain) {
        communicationStore.setCarDomain(carDomain)
    }

    override suspend fun getCarDomain(): CarDomain? = communicationStore.getCarDomain()

    override suspend fun clearCarDomain() {
        communicationStore.clearCarDomain()
    }

}