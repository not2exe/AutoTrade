package com.autotrade.fullscreencarfeature.ui.stateholders

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autotrade.common.carcommunication.CarCommunicationRepository
import com.autotrade.fullscreencarfeature.domain.CarsRepository
import com.autotrade.fullscreencarfeature.ui.CarRedactFormatter
import com.autotrade.fullscreencarfeature.ui.CarRedactVo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class CarRedactViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val carCommunicationRepository: CarCommunicationRepository,
    private val carsRepository: CarsRepository,
    private val carRedactFormatter: CarRedactFormatter,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): CarRedactViewModel
    }

    suspend fun getCarFromDataStore(): CarRedactVo =
        carRedactFormatter.format(carCommunicationRepository.getCarDomain())


    fun saveToFireStore(id: String, list: List<Pair<Int, String>>) {
        if (id.isEmpty()) {
            carsRepository.addToCollection(list)
        } else {
            carsRepository.editDocument(id, list)
        }
    }

    override fun onCleared() {
        viewModelScope.launch {
            carCommunicationRepository.clearCarDomain()
        }
        super.onCleared()
    }
}