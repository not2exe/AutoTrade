package com.autotrade.searchscreenfeature.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CarPagingSource(
    private val query: Query
) : PagingSource<QuerySnapshot, CarDto>() {

    override fun getRefreshKey(state: PagingState<QuerySnapshot, CarDto>): QuerySnapshot? {
        return null
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, CarDto> =
        withContext(Dispatchers.IO) {
            try {
                val currentPage = params.key ?: query.get().await()
                val lastVisibleCar = currentPage.documents[currentPage.size() - 1]
                val nextPage = query.startAfter(lastVisibleCar).get().await()
                LoadResult.Page(
                    data = currentPage.toObjects(CarDto::class.java),
                    prevKey = null,
                    nextKey = nextPage
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
}