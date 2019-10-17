package com.asimqasimzade.takeawaychallenge.data.model.presentation

data class RestaurantPresentationModel(
    val name: String,
    val status: RestaurantOpenStatus,
    val bestMatch: Float,
    val newest: Float,
    val rating: Float,
    val distance: Int,
    val popularity: Float,
    val averagePrice: Int,
    val deliveryCost: Int,
    val minCost: Int,
    var isFavorite: Boolean
)

enum class RestaurantOpenStatus {
    OPEN, ORDER_AHEAD, CLOSED, UNKNOWN
}