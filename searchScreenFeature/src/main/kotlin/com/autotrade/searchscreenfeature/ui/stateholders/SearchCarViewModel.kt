package com.autotrade.searchscreenfeature.ui.stateholders

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.autotrade.common.carcommunication.CarCommunicationRepository
import com.autotrade.common.carcommunication.CarDomain
import com.autotrade.common.carcommunication.CarFields
import com.autotrade.searchscreenfeature.domain.CarFiltersRepository
import com.autotrade.searchscreenfeature.domain.CarPagingRepository
import com.autotrade.searchscreenfeature.domain.GetQueryUseCase
import com.autotrade.searchscreenfeature.ui.CarFormatter
import com.autotrade.searchscreenfeature.ui.CarVo
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query.Direction
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchCarViewModel @AssistedInject constructor(
    private val carFormatter: CarFormatter,
    private val carPagingRepository: CarPagingRepository,
    private val carFiltersRepository: CarFiltersRepository,
    private val carCommunicationRepository: CarCommunicationRepository,
    private val getQueryUseCase: GetQueryUseCase,
    private val collectionReference: CollectionReference,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): SearchCarViewModel
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getCars(): Flow<PagingData<CarVo>> {
        return combine(
            carFiltersRepository.getFilters(),
            carFiltersRepository.getSortBy()
        ) { filters, sortBy -> Pair(filters, sortBy) }.flatMapLatest { (filters, sortBy) ->
            carPagingRepository.getCars(
                getQueryUseCase.execute(
                    collectionReference,
                    filters,
                    sortBy
                )
            ).map { data -> data.map(carFormatter::format) }
        }.cachedIn(viewModelScope)
    }

    suspend fun getFilters(): Map<String, String>? =
        carFiltersRepository.getFilters()
            .map { map: Map<String, Any> ->
                map.mapValues { el -> el.value.toString() }
            }.firstOrNull()

    suspend fun getSortedBy(): Pair<String, Direction>? =
        carFiltersRepository.getSortBy().firstOrNull()


    fun changeFilters(brand: String, model: String) {
        val map = mutableMapOf<String, Any>()
        if (brand.isNotEmpty()) {
            map[CarFields.BRAND.string] = brand
        }
        if (model.isNotEmpty()) {
            map[CarFields.MODEL.string] = model
        }
        carFiltersRepository.setFilters(map)
    }

    fun setOrderedBy(field: String, direction: Direction = Direction.DESCENDING) {
        carFiltersRepository.setSortBy(field to direction)
    }

    fun clearFilters() {
        carFiltersRepository.clearFilters()
    }

    fun clearOrderedBy() {
        carFiltersRepository.clearSortedBy()
    }

    fun isFilterOrSortByApplied(): Flow<Boolean> = combine(
        carFiltersRepository.getFilters(),
        carFiltersRepository.getSortBy()
    ) { filters, sortBy -> filters.isNotEmpty() || sortBy != null }

    fun onChooseCar(car: CarDomain) {
        viewModelScope.launch {
            carCommunicationRepository.setCarDomain(car)
        }
    }
}