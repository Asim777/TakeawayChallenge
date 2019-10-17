package com.asimqasimzade.takeawaychallenge.domain

import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import com.asimqasimzade.takeawaychallenge.framework.repositories.FavoritesRepository
import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers.computation

@Reusable
class AddFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    fun execute(favorite: RestaurantPresentationModel) =
        Observable.fromCallable {
            favoritesRepository.addFavorite(favorite)
        }
}