package com.asimqasimzade.takeawaychallenge.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.asimqasimzade.takeawaychallenge.domain.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(
    application: Application,
    protected val schedulerProvider: SchedulerProvider
) : AndroidViewModel(application) {

    protected val subscriptions = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}