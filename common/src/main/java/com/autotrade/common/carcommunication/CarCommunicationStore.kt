package com.autotrade.common.carcommunication

import com.autotrade.di.scopes.AppScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AppScope
class CarCommunicationStore @Inject constructor() {
    private var carDomain: CarDomain? = null

    suspend fun setCarDomain(carDomain: CarDomain) = withContext(Dispatchers.IO) {
        synchronized(this@CarCommunicationStore) {
            this@CarCommunicationStore.carDomain = carDomain
        }
    }

    suspend fun getCarDomain(): CarDomain? = withContext(Dispatchers.IO) {
        synchronized(this@CarCommunicationStore) {
            carDomain
        }
    }

    suspend fun clearCarDomain() = withContext(Dispatchers.IO) {
        synchronized(this@CarCommunicationStore) {
            carDomain = null
        }
    }
}