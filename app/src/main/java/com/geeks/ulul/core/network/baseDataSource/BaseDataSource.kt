package com.geeks.ulul.core.network.baseDataSource

import com.geeks.ulul.core.network.result.Resource
import retrofit2.Response

abstract class BaseDataSource {

    suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null || response.code() in 200..299) return Resource.Success(body)
            } else {
                return Resource.Error(response.message(), response.body())
            }
        } catch (e: Exception){
            return Resource.Error(e.message ?: e.toString(), null)
        }
        return Resource.Error(call().message(), null )
    }
}