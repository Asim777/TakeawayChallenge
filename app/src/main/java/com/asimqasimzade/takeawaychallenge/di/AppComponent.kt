package com.asimqasimzade.takeawaychallenge.di

import android.app.Application
import com.asimqasimzade.takeawaychallenge.TakeawayApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: TakeawayApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}