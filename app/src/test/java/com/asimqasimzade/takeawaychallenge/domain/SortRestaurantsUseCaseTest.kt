package com.asimqasimzade.takeawaychallenge.domain

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantOpenStatus
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import org.junit.Before
import org.junit.Test

class SortRestaurantsUseCaseTest {
    private lateinit var cut: SortRestaurantsUseCase


    @Before
    fun setUp() {
        cut = SortRestaurantsUseCase()
    }

    @Test
    fun `Given restaurantPresentationModel list and sortParameter null When execute Then return correct sorting`() {
        // Given
        val givenSortParameter = null

        val givenModelList = getGivenModelList()

        val expectedModelList =
            listOf(
                RestaurantPresentationModel(
                    name = "Royal Thai",
                    status = RestaurantOpenStatus.ORDER_AHEAD,
                    bestMatch = 2.0f,
                    newest = 133.0f,
                    rating = 4.5f,
                    distance = 2639,
                    popularity = 44.0f,
                    averagePrice = 1492,
                    deliveryCost = 150,
                    minCost = 2500,
                    isFavorite = true
                ),
                RestaurantPresentationModel(
                    name = "Daily Sushi",
                    status = RestaurantOpenStatus.CLOSED,
                    bestMatch = 9.0f,
                    newest = 221.0f,
                    rating = 4.0f,
                    distance = 1911,
                    popularity = 6.0f,
                    averagePrice = 1327,
                    deliveryCost = 200,
                    minCost = 1000,
                    isFavorite = true
                ),
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
                ),
                RestaurantPresentationModel(
                    name = "CIRO 1939",
                    status = RestaurantOpenStatus.OPEN,
                    bestMatch = 12.0f,
                    newest = 231.0f,
                    rating = 4.5f,
                    distance = 3957,
                    popularity = 79.0f,
                    averagePrice = 1762,
                    deliveryCost = 99,
                    minCost = 1300,
                    isFavorite = false
                ),
                RestaurantPresentationModel(
                    name = "Tandoori Express",
                    status = RestaurantOpenStatus.CLOSED,
                    bestMatch = 1.0f,
                    newest = 266.0f,
                    rating = 4.5f,
                    distance = 2308,
                    popularity = 123.0f,
                    averagePrice = 1146,
                    deliveryCost = 150,
                    minCost = 1300,
                    isFavorite = false
                )
            )


        // When
        val actualList = cut.execute(givenModelList, givenSortParameter)

        // Then
        actualList.test()
            .assertValue(expectedModelList)
            .assertNoErrors()
    }

    @Test
    fun `Given restaurantPresentationModel list and sortParameter best match When execute Then return correct sorting`() {
        // Given
        val givenSortParameter = "Best Match"

        val givenModelList = getGivenModelList()

        val expectedModelList =
            listOf(
                RestaurantPresentationModel(
                    name = "Royal Thai",
                    status = RestaurantOpenStatus.ORDER_AHEAD,
                    bestMatch = 2.0f,
                    newest = 133.0f,
                    rating = 4.5f,
                    distance = 2639,
                    popularity = 44.0f,
                    averagePrice = 1492,
                    deliveryCost = 150,
                    minCost = 2500,
                    isFavorite = true
                ),
                RestaurantPresentationModel(
                    name = "Daily Sushi",
                    status = RestaurantOpenStatus.CLOSED,
                    bestMatch = 9.0f,
                    newest = 221.0f,
                    rating = 4.0f,
                    distance = 1911,
                    popularity = 6.0f,
                    averagePrice = 1327,
                    deliveryCost = 200,
                    minCost = 1000,
                    isFavorite = true
                ),
                RestaurantPresentationModel(
                    name = "CIRO 1939",
                    status = RestaurantOpenStatus.OPEN,
                    bestMatch = 12.0f,
                    newest = 231.0f,
                    rating = 4.5f,
                    distance = 3957,
                    popularity = 79.0f,
                    averagePrice = 1762,
                    deliveryCost = 99,
                    minCost = 1300,
                    isFavorite = false
                ),
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
                ),
                RestaurantPresentationModel(
                    name = "Tandoori Express",
                    status = RestaurantOpenStatus.CLOSED,
                    bestMatch = 1.0f,
                    newest = 266.0f,
                    rating = 4.5f,
                    distance = 2308,
                    popularity = 123.0f,
                    averagePrice = 1146,
                    deliveryCost = 150,
                    minCost = 1300,
                    isFavorite = false
                )
            )


        // When
        val actualList = cut.execute(givenModelList, givenSortParameter)

        // Then
        actualList.test()
            .assertValue(expectedModelList)
            .assertNoErrors()
    }

    private fun getGivenModelList() = listOf(
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
        ),
        RestaurantPresentationModel(
            name = "Tandoori Express",
            status = RestaurantOpenStatus.CLOSED,
            bestMatch = 1.0f,
            newest = 266.0f,
            rating = 4.5f,
            distance = 2308,
            popularity = 123.0f,
            averagePrice = 1146,
            deliveryCost = 150,
            minCost = 1300,
            isFavorite = false
        ),
        RestaurantPresentationModel(
            name = "Royal Thai",
            status = RestaurantOpenStatus.ORDER_AHEAD,
            bestMatch = 2.0f,
            newest = 133.0f,
            rating = 4.5f,
            distance = 2639,
            popularity = 44.0f,
            averagePrice = 1492,
            deliveryCost = 150,
            minCost = 2500,
            isFavorite = true
        ),
        RestaurantPresentationModel(
            name = "Daily Sushi",
            status = RestaurantOpenStatus.CLOSED,
            bestMatch = 9.0f,
            newest = 221.0f,
            rating = 4.0f,
            distance = 1911,
            popularity = 6.0f,
            averagePrice = 1327,
            deliveryCost = 200,
            minCost = 1000,
            isFavorite = true
        ),
        RestaurantPresentationModel(
            name = "CIRO 1939",
            status = RestaurantOpenStatus.OPEN,
            bestMatch = 12.0f,
            newest = 231.0f,
            rating = 4.5f,
            distance = 3957,
            popularity = 79.0f,
            averagePrice = 1762,
            deliveryCost = 99,
            minCost = 1300,
            isFavorite = false
        )
    )
}