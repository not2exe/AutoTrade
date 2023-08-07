package com.autotrade.fullscreencarfeature.domain

interface CarsRepository {
    fun addToCollection(list: List<Pair<Int, String>>)

    fun editDocument(documentId: String, list: List<Pair<Int, String>>)
}