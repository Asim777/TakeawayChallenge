package com.asimqasimzade.takeawaychallenge.di

import com.asimqasimzade.takeawaychallenge.presentation.home.HomeActivity
import com.asimqasimzade.takeawaychallenge.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun bindHomeActivity(): HomeActivity

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideHomeViewModelFactory(viewModel: HomeViewModel): ViewModelProviderFactory<HomeViewModel> {
            return ViewModelProviderFactory(viewModel)
        }
    }

}