package com.asimqasimzade.takeawaychallenge.framework.remote

import com.asimqasimzade.takeawaychallenge.data.RestaurantsDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRestaurantsDataSource @Inject constructor(
    private val restaurantsService: RestaurantsService
) : RestaurantsDataSource {
    override  fun getAll() = restaurantsService.getRestaurants()
}