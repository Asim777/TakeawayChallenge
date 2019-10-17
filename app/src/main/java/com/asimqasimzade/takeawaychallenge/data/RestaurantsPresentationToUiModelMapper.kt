package com.asimqasimzade.takeawaychallenge.data

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantOpenStatus
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import com.asimqasimzade.takeawaychallenge.data.model.ui.RestaurantUiModel
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class RestaurantsPresentationToUiModelMapper {
    fun map(restaurantPresentation: RestaurantPresentationModel) =
        getRestaurantUiModel(restaurantPresentation)

    private fun getRestaurantUiModel(restaurantPresentation: RestaurantPresentationModel) =
        RestaurantUiModel(
            name = restaurantPresentation.name,
            status = getRestaurantOpenStatus(restaurantPresentation.status),
            bestMatch = getFriendlyBestMatch(restaurantPresentation.bestMatch),
            newest = getFriendlyNewest(restaurantPresentation.newest),
            rating = getFriendlyRating(restaurantPresentation.rating),
            distance = getFriendlyDistance(restaurantPresentation.distance),
            popularity = restaurantPresentation.popularity.toInt(),
            averagePrice = restaurantPresentation.averagePrice,
            deliveryCosts = getFriendlyCost(restaurantPresentation.deliveryCost),
            minCost = getFriendlyCost(restaurantPresentation.minCost),
            isFavorite = restaurantPresentation.isFavorite
        )

    private fun getFriendlyNewest(newest: Float): Boolean = newest > 200.0f

    private fun getFriendlyBestMatch(bestMatch: Float) = bestMatch > 300.0f

    private fun getFriendlyRating(rating: Float) = rating.roundToInt()

    private fun getFriendlyDistance(distance: Int) =
        if (distance > 1000) {
            "${"%.2f".format(distance.toFloat() / 1000)} km"
        } else {
            "$distance m"
        }

    private fun getFriendlyCost(cost: Int) = "€ $cost"

    private fun getRestaurantOpenStatus(status: RestaurantOpenStatus) =
        when (status) {
            RestaurantOpenStatus.OPEN -> "Open"
            RestaurantOpenStatus.CLOSED -> "Closed"
            RestaurantOpenStatus.ORDER_AHEAD -> "Order Ahead"
            RestaurantOpenStatus.UNKNOWN -> ""
        }
}