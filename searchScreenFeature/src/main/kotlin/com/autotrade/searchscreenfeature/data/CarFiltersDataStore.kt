package com.autotrade.searchscreenfeature.data

import com.autotrade.di.scopes.AppScope
import com.google.firebase.firestore.Query.Direction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@AppScope
class CarFiltersDataStore @Inject constructor() {
    private val filters = MutableStateFlow<Map<String, Any>>(mapOf())
    private val sortBy = MutableStateFlow<Pair<String, Direction>?>(null)

    fun getSortBy(): Flow<Pair<String, Direction>?> = sortBy

    fun getFilters(): Flow<Map<String, Any>> =
        filters
    fun setFilters(filters: Map<String, Any>) {
        this.filters.value = filters
    }

    fun setSortBy(sortBy: Pair<String, Direction>) {
        this.sortBy.value = sortBy
    }

    fun clearFilters() {
        filters.value = mapOf()
        sortBy.value = null
    }

    fun clearSortedBy() {
        sortBy.value = null
    }
}