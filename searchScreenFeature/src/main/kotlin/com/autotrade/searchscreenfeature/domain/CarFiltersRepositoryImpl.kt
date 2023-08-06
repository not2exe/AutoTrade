package com.autotrade.searchscreenfeature.domain

import com.autotrade.searchscreenfeature.data.CarFiltersDataStore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarFiltersRepositoryImpl @Inject constructor(
    private val carFiltersDataStore: CarFiltersDataStore
) :
    CarFiltersRepository {
    override fun getSortBy(): Flow<Pair<String, Query.Direction>?> =
        carFiltersDataStore.getSortBy()

    override fun getFilters(): Flow<Map<String, Any>> =
        carFiltersDataStore.getFilters()

    override fun setFilters(filters: Map<String, Any>) =
        carFiltersDataStore.setFilters(filters)

    override fun setSortBy(sortBy: Pair<String, Query.Direction>) =
        carFiltersDataStore.setSortBy(sortBy)

    override fun clearFilters() = carFiltersDataStore.clearFilters()
    override fun clearSortedBy() = carFiltersDataStore.clearSortedBy()
}