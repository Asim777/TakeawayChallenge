package com.asimqasimzade.takeawaychallenge.di

import com.asimqasimzade.takeawaychallenge.presentation.restaurants.RestaurantsFragment
import com.asimqasimzade.takeawaychallenge.presentation.restaurants.RestaurantsViewModel
import com.asimqasimzade.takeawaychallenge.presentation.splash.SplashFragment
import com.asimqasimzade.takeawaychallenge.presentation.splash.SplashViewModel
import dagger.*
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun bindRestaurantsFragment(): RestaurantsFragment

    @ContributesAndroidInjector
    internal abstract fun bindSplashFragment(): SplashFragment

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideRestaurantsViewModelFactory(viewModel: RestaurantsViewModel): ViewModelProviderFactory<RestaurantsViewModel> {
            return ViewModelProviderFactory(viewModel)
        }

        @Provides
        @JvmStatic
        internal fun provideSplashViewModelFactory(viewModel: SplashViewModel): ViewModelProviderFactory<SplashViewModel> {
            return ViewModelProviderFactory(viewModel)
        }
    }
}