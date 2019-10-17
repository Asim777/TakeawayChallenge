package com.asimqasimzade.takeawaychallenge.presentation.base

import android.app.Application
import com.asimqasimzade.takeawaychallenge.domain.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

interface BaseViewModelInputs {
    fun onResume()
    fun onCreate()
    fun onStart()
    fun onStop()
}

interface BaseViewModelOutputs {
    fun showError(): Observable<String>
}

open class LifeCycleAwareViewModel(
    application: Application,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(application, schedulerProvider),
    BaseViewModelInputs,
    BaseViewModelOutputs {

    open val inputs: BaseViewModelInputs
        get() = this

    open val outputs: BaseViewModelOutputs
        get() = this

    protected val showError: Subject<String> = PublishSubject.create()
    protected val refreshing: Subject<Boolean> = BehaviorSubject.createDefault(false)

    override fun onCreate() {}
    override fun onResume() {}
    override fun onStart() {}
    override fun onStop() {}

    override fun showError() : Observable<String> = showError.observeOn(schedulerProvider.ui()).hide()

    internal fun <T> Observable<T>.observeOnUiAndHide() = observeOn(schedulerProvider.ui()).hide()

}
