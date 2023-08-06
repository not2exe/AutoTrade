package com.autotrade.searchscreenfeature.domain

import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow

interface CarFiltersRepository {
    fun getSortBy(): Flow<Pair<String, Query.Direction>?>

    fun getFilters(): Flow<Map<String, Any>>

    fun setFilters(filters: Map<String, Any>)

    fun setSortBy(sortBy: Pair<String, Query.Direction>)

    fun clearFilters()

    fun clearSortedBy()
}