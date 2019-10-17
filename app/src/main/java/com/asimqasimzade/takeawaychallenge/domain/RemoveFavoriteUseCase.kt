package com.asimqasimzade.takeawaychallenge.domain

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import com.asimqasimzade.takeawaychallenge.framework.repositories.FavoritesRepository
import dagger.Reusable
import io.reactivex.Observable
import javax.inject.Inject

@Reusable
class RemoveFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    fun execute(favorite: RestaurantPresentationModel) =
        Observable.fromCallable {
            favoritesRepository.removeFavorite(favorite)
        }
}