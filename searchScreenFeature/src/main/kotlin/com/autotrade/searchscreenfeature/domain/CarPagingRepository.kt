package com.autotrade.searchscreenfeature.domain

import androidx.paging.PagingData
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow

interface CarPagingRepository {

    fun getCars(query: Query): Flow<PagingData<CarDomain>>
}