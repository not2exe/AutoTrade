package com.autotrade.searchscreenfeature.ui.stateholders

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.autotrade.searchscreenfeature.ui.CarVo
import com.autotrade.searchscreenfeature.domain.CarFiltersRepository
import com.autotrade.searchscreenfeature.domain.CarPagingRepository
import com.autotrade.searchscreenfeature.domain.GetQueryUseCase
import com.autotrade.searchscreenfeature.ui.CarFormatter
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query.Direction
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class SearchCarViewModel @AssistedInject constructor(
    private val carsCollectionReference: CollectionReference,
    private val carFormatter: CarFormatter,
    private val carPagingRepository: CarPagingRepository,
    private val carFiltersRepository: CarFiltersRepository,
    private val getQueryUseCase: GetQueryUseCase,
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
                    carsCollectionReference,
                    filters,
                    sortBy
                )
            ).map { data -> data.map(carFormatter::format) }
        }
    }

    fun getFilters(): Flow<Map<String, String>> =
        carFiltersRepository.getFilters()
            .map { map: Map<String, Any> ->
                map.mapValues { el -> el.value.toString() }
            }

    fun getSortedBy(): Flow<String?> = carFiltersRepository.getSortBy().map { pair -> pair?.first }


    fun changeFilters(brand: String, model: String) {
        val map = mutableMapOf<String, Any>()
        if (brand.isNotEmpty()) {
            map["brand"] = brand
        }
        if (model.isNotEmpty()) {
            map["model"] = model
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
}