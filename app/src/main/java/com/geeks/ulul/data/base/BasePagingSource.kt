package com.geeks.ulul.data.base

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.Response

abstract class BasePagingSource<Value : Any>(
    private val request: suspend (position: Int) -> Response<BasePagingResponse<Value>>
) : PagingSource<Int, Value>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return try {
            val currentItem = params.key ?: 0
            val response = request(currentItem)
            val responseData = mutableListOf<Value>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentItem == 0) null else -1,
                nextKey = currentItem.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}