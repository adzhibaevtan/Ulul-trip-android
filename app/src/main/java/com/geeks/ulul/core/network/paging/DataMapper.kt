package com.geeks.ulul.core.network.paging

interface DataMapper<T> {
    fun responseToModel(): List<T>
}