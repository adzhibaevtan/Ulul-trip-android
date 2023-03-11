package com.geeks.ulul.data.util

import com.geeks.ulul.data.model.FilterModel


fun FilterModel.changeFilter(newFilter :FilterModel){
    this.category = newFilter.category
    this.date_departure = newFilter.date_departure
    this.complexity = newFilter.complexity
    this.duration = newFilter.duration
    this.price_max = newFilter.price_max
}