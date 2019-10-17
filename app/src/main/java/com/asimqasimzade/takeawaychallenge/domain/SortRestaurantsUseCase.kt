package com.asimqasimzade.takeawaychallenge.domain

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class SortRestaurantsUseCase @Inject constructor() {
    fun execute(
        listOfRestaurants: List<RestaurantPresentationModel>,
        sortParameter: String?
    ): Single<List<RestaurantPresentationModel>> = Single.just(
        if (sortParameter != null) {
            when (sortParameter) {
                "Best Match" -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                            .thenByDescending { it.bestMatch }
                    )
                }
                "Newest" -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                            .thenByDescending { it.newest }
                    )
                }
                "Rating" -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                            .thenByDescending { it.rating }
                    )
                }
                "Distance" -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                            .thenBy { it.distance }
                    )
                }
                "Popularity" -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                            .thenByDescending { it.popularity }
                    )
                }
                "Average price" -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                            .thenBy { it.averagePrice }
                    )
                }
                "Delivery cost" -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                            .thenBy { it.deliveryCost }
                    )
                }
                "Minimum cost" -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                            .thenBy { it.minCost }
                    )
                }
                else -> {
                    listOfRestaurants.sortedWith(
                        compareByDescending(RestaurantPresentationModel::isFavorite)
                            .thenBy { it.status }
                    )
                }
            }
        } else {
            listOfRestaurants.sortedWith(
                compareByDescending(RestaurantPresentationModel::isFavorite)
                    .thenBy { it.status }
            )
        }
    )
}