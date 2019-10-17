package com.asimqasimzade.takeawaychallenge.framework.repositories

import com.asimqasimzade.takeawaychallenge.data.FavoritesDataSource
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val favoritesDataSource: FavoritesDataSource) {
     fun getAllFavorites() = favoritesDataSource.getAll()

     fun addFavorite(favorite: RestaurantPresentationModel) =
        favoritesDataSource.add(favorite)

     fun removeFavorite(favorite: RestaurantPresentationModel) =
        favoritesDataSource.remove(favorite)
}