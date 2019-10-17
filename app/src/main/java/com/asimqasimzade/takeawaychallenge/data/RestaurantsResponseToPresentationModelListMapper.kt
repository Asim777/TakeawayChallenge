package com.asimqasimzade.takeawaychallenge.data

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantOpenStatus
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import com.asimqasimzade.takeawaychallenge.data.model.response.RestaurantResponseModel
import com.asimqasimzade.takeawaychallenge.data.model.response.RestaurantsResponse
import javax.inject.Singleton

@Singleton
class RestaurantsResponseToPresentationModelListMapper {
    fun map(restaurantsResponse: RestaurantsResponse): List<RestaurantPresentationModel> {
        val restaurantsList = mutableListOf<RestaurantPresentationModel>()
        restaurantsResponse.restaurants.forEach { restaurantResponse ->
            val restaurantPresentationModel = getRestaurantPresentationModel(restaurantResponse)
            restaurantsList.add(restaurantPresentationModel)
        }
        return restaurantsList
    }

    private fun getRestaurantPresentationModel(restaurantResponse: RestaurantResponseModel) =
        RestaurantPresentationModel(
            name = restaurantResponse.name,
            status = getRestaurantOpenStatus(restaurantResponse.status),
            bestMatch = restaurantResponse.sortingValues.bestMatch,
            newest = restaurantResponse.sortingValues.newest,
            rating = restaurantResponse.sortingValues.ratingAverage,
            distance = restaurantResponse.sortingValues.distance,
            popularity = restaurantResponse.sortingValues.popularity,
            averagePrice = restaurantResponse.sortingValues.averageProductPrice,
            deliveryCost = restaurantResponse.sortingValues.deliveryCost,
            minCost = restaurantResponse.sortingValues.minCost,
            isFavorite = false
        )

    private fun getRestaurantOpenStatus(status: String) =
        when (status) {
            "open" -> RestaurantOpenStatus.OPEN
            "closed" -> RestaurantOpenStatus.CLOSED
            "order ahead" -> RestaurantOpenStatus.ORDER_AHEAD
            else -> RestaurantOpenStatus.UNKNOWN
        }
}