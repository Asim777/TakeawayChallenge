package com.asimqasimzade.takeawaychallenge

import com.asimqasimzade.takeawaychallenge.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class TakeawayApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)
        return appComponent
    }
}