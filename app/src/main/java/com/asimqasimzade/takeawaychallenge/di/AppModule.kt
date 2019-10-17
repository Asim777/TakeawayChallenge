package com.asimqasimzade.takeawaychallenge.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.asimqasimzade.takeawaychallenge.domain.SchedulerProvider
import com.asimqasimzade.takeawaychallenge.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    fun provideResources(app: Application): Resources = app.resources

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()
}
