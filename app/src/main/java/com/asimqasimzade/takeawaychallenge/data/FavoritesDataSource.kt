package com.asimqasimzade.takeawaychallenge.data

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import io.reactivex.Observable
import io.reactivex.Single

interface FavoritesDataSource {
     fun getAll(): Single<List<RestaurantPresentationModel>>
     fun add(favorite: RestaurantPresentationModel)
     fun remove(favorite: RestaurantPresentationModel)
}