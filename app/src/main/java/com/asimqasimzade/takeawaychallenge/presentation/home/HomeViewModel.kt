package com.asimqasimzade.takeawaychallenge.presentation.home

import android.app.Application
import com.asimqasimzade.takeawaychallenge.domain.SchedulerProvider
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseViewModelInputs
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseViewModelOutputs
import com.asimqasimzade.takeawaychallenge.presentation.base.LifeCycleAwareViewModel
import javax.inject.Inject

interface HomeViewModelInputs : BaseViewModelInputs {}

interface HomeViewModelOutputs : BaseViewModelOutputs {}

class HomeViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider
) : LifeCycleAwareViewModel(application, schedulerProvider),
    HomeViewModelInputs,
    HomeViewModelOutputs {

    override val inputs: HomeViewModelInputs
        get() = this

    override val outputs: HomeViewModelOutputs
        get() = this
}