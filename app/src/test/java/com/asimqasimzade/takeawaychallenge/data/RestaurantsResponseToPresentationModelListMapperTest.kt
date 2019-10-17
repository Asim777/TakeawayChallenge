package com.asimqasimzade.takeawaychallenge.data

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantOpenStatus
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import com.asimqasimzade.takeawaychallenge.data.model.response.RestaurantResponseModel
import com.asimqasimzade.takeawaychallenge.data.model.response.RestaurantsResponse
import com.asimqasimzade.takeawaychallenge.data.model.response.SortingValuesModelResponseModel
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class RestaurantsResponseToPresentationModelListMapperTest {
    private lateinit var cut: RestaurantsResponseToPresentationModelListMapper

    @Before
    fun setUp() {
        cut = RestaurantsResponseToPresentationModelListMapper()
    }

    @Test
    fun `Given restaurantResponseModel When map Then returns restaurantPresentationModel`() {
        // Given
        val givenModel = RestaurantsResponse(
            restaurants = listOf(
                RestaurantResponseModel(
                    name = "Tanoshii Sushi",
                    status = "open",
                    sortingValues = SortingValuesModelResponseModel(
                        bestMatch = 0.0f,
                        newest = 96.0f,
                        ratingAverage = 4.5f,
                        distance = 1190,
                        popularity = 17.0f,
                        averageProductPrice = 1536,
                        deliveryCost = 0,
                        minCost = 1000
                    )
                )
            )
        )

        val expectedModel = listOf(
            RestaurantPresentationModel(
                name = "Tanoshii Sushi",
                status = RestaurantOpenStatus.OPEN,
                bestMatch = 0.0f,
                newest = 96.0f,
                rating = 4.5f,
                distance = 1190,
                popularity = 17.0f,
                averagePrice = 1536,
                deliveryCost = 0,
                minCost = 1000,
                isFavorite = false
            )
        )

        // When
        val actualModel = cut.map(givenModel)

        // Then
        Assert.assertEquals(expectedModel, actualModel)
    }
}