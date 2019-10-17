package com.asimqasimzade.takeawaychallenge.presentation.splash

import android.app.Application
import com.asimqasimzade.takeawaychallenge.domain.SchedulerProvider
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseViewModelInputs
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseViewModelOutputs
import com.asimqasimzade.takeawaychallenge.presentation.base.LifeCycleAwareViewModel
import javax.inject.Inject

interface SplashViewModelInputs : BaseViewModelInputs {}

interface SplashViewModelOutputs : BaseViewModelOutputs {}

class SplashViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider
) : LifeCycleAwareViewModel(application, schedulerProvider), SplashViewModelInputs,
    SplashViewModelOutputs {
    override val inputs: SplashViewModelInputs
        get() = this

    override val outputs: SplashViewModelOutputs
        get() = this
}