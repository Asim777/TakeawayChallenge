package com.asimqasimzade.takeawaychallenge.di

import com.asimqasimzade.takeawaychallenge.framework.remote.RestaurantsService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGetRestaurantsService(): RestaurantsService =
        Retrofit.Builder()
            .baseUrl(RestaurantsService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(RestaurantsService::class.java)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}