package com.geeks.ulul.data.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geeks.ulul.core.network.paging.BasePagingSource
import com.geeks.ulul.core.network.paging.DataMapper
import com.geeks.ulul.core.network.result.Resource
import com.geeks.ulul.data.util.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

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

    protected fun <ValueDto : DataMapper<Value>, Value : Any> doPagingRequest(
        pagingSource: BasePagingSource<ValueDto, Value>,
        pageSize: Int = 10,
        prefetchDistance: Int = pageSize,
        enablePlaceholders: Boolean = true,
        initialLoadSize: Int = pageSize * 3,
        maxSize: Int = Int.MAX_VALUE,
        jumpThreshold: Int = Int.MIN_VALUE
    ): Flow<PagingData<Value>> {
        return Pager(
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

    protected fun <T> doRequest(response: suspend () -> Resource<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(response().data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "unknown exception"))
        }
    }.flowOn(Dispatchers.IO)
}