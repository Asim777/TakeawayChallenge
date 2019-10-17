package com.asimqasimzade.takeawaychallenge.domain

import com.asimqasimzade.takeawaychallenge.framework.repositories.FavoritesRepository
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    fun execute() = favoritesRepository.getAllFavorites()
}