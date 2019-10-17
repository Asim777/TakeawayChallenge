package com.asimqasimzade.takeawaychallenge.data

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantOpenStatus
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import com.asimqasimzade.takeawaychallenge.data.model.ui.RestaurantUiModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RestaurantsPresentationToUiModelMapperTest {

    private lateinit var cut: RestaurantsPresentationToUiModelMapper

    @Before
    fun setUp() {
        cut = RestaurantsPresentationToUiModelMapper()
    }

    @Test
    fun `Given restaurantPresentationModel When map Then returns restaurantUiModel`() {
        // Given
        val givenModel = RestaurantPresentationModel(
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

        val expectedModel = RestaurantUiModel(
            name = "Tanoshii Sushi",
            status = "Open",
            bestMatch = false,
            newest = false,
            rating = 5,
            distance = "1.19 km",
            popularity = 17,
            averagePrice = 1536,
            deliveryCosts = "€ 0",
            minCost = "€ 1000",
            isFavorite = false
        )

        // When
        val actualModel = cut.map(givenModel)

        // Then
        assertEquals(expectedModel, actualModel)
    }
}