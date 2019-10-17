package com.asimqasimzade.takeawaychallenge.data.model.response

data class RestaurantResponseModel(
    val name: String,
    val status: String,
    val sortingValues: SortingValuesModelResponseModel
)