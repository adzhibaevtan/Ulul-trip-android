package com.geeks.ulul.data.util

import android.content.Intent
import com.geeks.ulul.data.model.FilterModel
import com.geeks.ulul.data.model.Region


fun FilterModel.changeFilter(newFilter :FilterModel){
    this.category = newFilter.category
    this.date_departure = newFilter.date_departure
    this.complexity = newFilter.complexity
    this.duration = newFilter.duration
    this.price_max = newFilter.price_max
}

fun List<Region>.getRegions(): String {
    var result = ""
    if (this.isNotEmpty()) {
        for (i in 0 until this.size) {
            result += if (i != this.size - 1) {
                this[i].name + ", "
            } else {
                this[i].name
            }
        }
    }
    return result
}

fun Intent.share(text: String?): Intent? {
    this.apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text ?: "")
        type = "text/plain"
    }
    return Intent.createChooser(this, null)
}