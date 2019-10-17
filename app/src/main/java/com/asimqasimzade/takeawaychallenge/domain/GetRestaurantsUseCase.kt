package com.asimqasimzade.takeawaychallenge.domain

import com.asimqasimzade.takeawaychallenge.data.RestaurantsResponseToPresentationModelListMapper
import com.asimqasimzade.takeawaychallenge.framework.repositories.RestaurantsRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetRestaurantsUseCase @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    private val responseToPresentationMapper: RestaurantsResponseToPresentationModelListMapper
) {
    fun execute() = restaurantsRepository.getAllRestaurants().map { restaurantsResponse ->
            responseToPresentationMapper.map(restaurantsResponse)
        }
}