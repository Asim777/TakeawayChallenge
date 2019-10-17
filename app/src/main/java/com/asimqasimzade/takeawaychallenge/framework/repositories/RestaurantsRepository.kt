package com.asimqasimzade.takeawaychallenge.framework.repositories

import com.asimqasimzade.takeawaychallenge.data.RestaurantsDataSource
import javax.inject.Inject

class RestaurantsRepository @Inject constructor(
    private val restaurantsDataSource: RestaurantsDataSource
) {
    fun getAllRestaurants() = restaurantsDataSource.getAll()
}