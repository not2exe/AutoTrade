package com.autotrade.searchscreenfeature.domain

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Query.Direction
import javax.inject.Inject

class GetQueryUseCase @Inject constructor() {

    fun execute(
        collectionReference: CollectionReference,
        filtersEqual: Map<String, Any> = mapOf(),
        sortBy: Pair<String, Direction>? = null
    ): Query {
        var query: Query = collectionReference
        filtersEqual.forEach { entry ->
            query = query.whereEqualTo(entry.key, entry.value)
        }
        if (sortBy != null) {
            query = query.orderBy(sortBy.first, sortBy.second)
        }
        return query
    }
}