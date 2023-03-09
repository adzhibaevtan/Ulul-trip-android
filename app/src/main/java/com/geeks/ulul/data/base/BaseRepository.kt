package com.geeks.ulul.data.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.geeks.ulul.data.util.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {
    protected fun <T> makeNetworkRequest(
        gatherIfSucceed: ((T) -> Unit)? = null,
        request: suspend () -> T
    ) =
        flow<Either<String, T>> {
            request().also {
                gatherIfSucceed?.invoke(it)
                emit(Either.Right(value = it))
            }
        }.flowOn(Dispatchers.IO).catch { exception ->
            emit(Either.Left(value = exception.localizedMessage ?: "Error Occurred!"))
        }

    protected fun <Value : Any> makePagingRequest(
        pagingSource: BasePagingSource<Value>,
        pageSize: Int = 20,
        prefetchDistance: Int = pageSize,
        enablePlaceholders: Boolean = true,
        initialLoadSize: Int = pageSize * 3,
        maxSize: Int = Int.MAX_VALUE,
        jumpThreshold: Int = Int.MIN_VALUE,
    ) =
        Pager(
            config = PagingConfig(
                pageSize,
                prefetchDistance,
                enablePlaceholders,
                initialLoadSize,
                maxSize,
                jumpThreshold
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
}