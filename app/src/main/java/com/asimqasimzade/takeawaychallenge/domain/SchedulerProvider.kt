package com.asimqasimzade.takeawaychallenge.domain

import dagger.Reusable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Reusable
class SchedulerProvider {
    fun io(): Scheduler = Schedulers.io()
    fun ui(): Scheduler = AndroidSchedulers.mainThread()
    fun computation() = Schedulers.computation()
    fun newThread() = Schedulers.newThread()
}