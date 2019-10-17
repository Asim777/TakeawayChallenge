package com.asimqasimzade.takeawaychallenge.di

import android.app.Application
import com.asimqasimzade.takeawaychallenge.data.FavoritesDataSource
import com.asimqasimzade.takeawaychallenge.data.RestaurantsDataSource
import com.asimqasimzade.takeawaychallenge.data.RestaurantsPresentationToUiModelMapper
import com.asimqasimzade.takeawaychallenge.data.RestaurantsResponseToPresentationModelListMapper
import com.asimqasimzade.takeawaychallenge.domain.SchedulerProvider
import com.asimqasimzade.takeawaychallenge.framework.RoomFavoriteRestaurantsDataSource
import com.asimqasimzade.takeawaychallenge.framework.remote.RemoteRestaurantsDataSource
import com.asimqasimzade.takeawaychallenge.framework.remote.RestaurantsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRestaurantsDataSource(
        restaurantsService: RestaurantsService
    ): RestaurantsDataSource = RemoteRestaurantsDataSource(restaurantsService)

    @Singleton
    @Provides
    fun provideFavoritesDataSource(
        application: Application
    ): FavoritesDataSource = RoomFavoriteRestaurantsDataSource(application)

    @Singleton
    @Provides
    fun provideRestaurantsResponseToPresentationModelListMapper() =
        RestaurantsResponseToPresentationModelListMapper()

    @Singleton
    @Provides
    fun restaurantsPresentationToUiModelMapper() = RestaurantsPresentationToUiModelMapper()
}