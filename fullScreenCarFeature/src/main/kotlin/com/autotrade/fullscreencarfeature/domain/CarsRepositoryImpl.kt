package com.autotrade.fullscreencarfeature.domain

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(
    private val requestCarMapper: RequestCarMapper,
    private val coroutineScope: CoroutineScope,
    private val collectionReference: CollectionReference,
) :
    CarsRepository {

    override fun addToCollection(list: List<Pair<Int, String>>) {
        coroutineScope.launch {
            collectionReference.add(requestCarMapper.map(list)).await()
        }
    }

    override fun editDocument(documentId: String, list: List<Pair<Int, String>>) {
        coroutineScope.launch {
            collectionReference.document(documentId).set(
                requestCarMapper.map(list)
            ).await()
        }
    }
}