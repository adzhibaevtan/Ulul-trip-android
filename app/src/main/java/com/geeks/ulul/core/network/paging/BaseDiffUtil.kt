package com.geeks.ulul.core.network.paging

import androidx.recyclerview.widget.DiffUtil


interface BaseDiffModel<T> {
    val id: T
    override fun equals(other: Any?): Boolean
}

open class BaseDiffUtil<T : BaseDiffModel<S>, S> :
    DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}