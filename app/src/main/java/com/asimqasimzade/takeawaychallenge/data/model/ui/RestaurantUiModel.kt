package com.asimqasimzade.takeawaychallenge.data.model.ui

data class RestaurantUiModel(
    val name: String,
    val status: String,
    val bestMatch: Boolean,
    val newest: Boolean,
    val rating: Int,
    val distance: String,
    val popularity: Int,
    val averagePrice: Int,
    val deliveryCosts: String,
    val minCost: String,
    val isFavorite: Boolean
)
