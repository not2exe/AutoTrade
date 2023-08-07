package com.autotrade.searchscreenfeature.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.autotrade.common.carcommunication.CarDomain
import com.autotrade.searchscreenfeature.data.CarPagingSource
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarPagingRepositoryImpl @Inject constructor(private val carMapper: CarMapper) :
    CarPagingRepository {

    override fun getCars(query: Query): Flow<PagingData<CarDomain>> {
        return Pager(
            PagingConfig(pageSize = PAGE_SIZE)
        ) { CarPagingSource(query) }.flow.map { data ->
            data.filter { car -> car.body != null }.map(carMapper::map)
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}