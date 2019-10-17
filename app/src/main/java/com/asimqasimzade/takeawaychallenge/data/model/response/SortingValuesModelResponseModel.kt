package com.asimqasimzade.takeawaychallenge.data.model.response

data class SortingValuesModelResponseModel(
    val bestMatch: Float,
    val newest: Float,
    val ratingAverage: Float,
    val distance: Int,
    val popularity: Float,
    val averageProductPrice: Int,
    val deliveryCost: Int,
    val minCost: Int
)