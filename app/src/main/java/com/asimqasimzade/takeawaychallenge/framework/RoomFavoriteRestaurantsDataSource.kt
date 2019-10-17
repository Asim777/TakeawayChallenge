package com.asimqasimzade.takeawaychallenge.framework

import android.app.Application
import com.asimqasimzade.takeawaychallenge.data.FavoritesDataSource
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantOpenStatus
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import com.asimqasimzade.takeawaychallenge.framework.db.RestaurantEntity
import com.asimqasimzade.takeawaychallenge.framework.db.RestaurantsDatabase
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomFavoriteRestaurantsDataSource @Inject constructor(
    application: Application
) : FavoritesDataSource {

    private val favoriteRestaurantDao =
        RestaurantsDatabase.getInstance(application.applicationContext).favoriteRestaurantDao()

    override fun getAll(): Single<List<RestaurantPresentationModel>> =
        favoriteRestaurantDao.getFavoriteRestaurants().map { listOfEntity ->
            listOfEntity.map {
                getRestaurantPresentationModel(it)
            }
    }

    override fun add(favorite: RestaurantPresentationModel) =
        favoriteRestaurantDao.addFavoriteRestaurant(getRestaurantEntity(favorite))

    override fun remove(favorite: RestaurantPresentationModel) =
        favoriteRestaurantDao.removeFavoriteRestaurant(getRestaurantEntity(favorite))

    private fun getRestaurantEntity(restaurant: RestaurantPresentationModel) =
        with(restaurant) {
            RestaurantEntity(
                name,
                status.name,
                bestMatch,
                newest,
                rating,
                distance,
                popularity,
                averagePrice,
                deliveryCost,
                minCost
            )
        }

    private fun getRestaurantPresentationModel(restaurant: RestaurantEntity) =
        with(restaurant) {
            RestaurantPresentationModel(
                name,
                getRestaurantOpenStatus(status),
                bestMatch,
                newest,
                rating,
                distance,
                popularity,
                averagePrice,
                deliveryCosts,
                minCost,
                true
            )
        }

    private fun getRestaurantOpenStatus(status: String) =
        when (status) {
            "OPEN" -> RestaurantOpenStatus.OPEN
            "CLOSED" -> RestaurantOpenStatus.CLOSED
            "ORDER_AHEAD" -> RestaurantOpenStatus.ORDER_AHEAD
            else -> RestaurantOpenStatus.UNKNOWN
        }
}

