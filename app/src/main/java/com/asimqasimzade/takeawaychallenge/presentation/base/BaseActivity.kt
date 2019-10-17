package com.asimqasimzade.takeawaychallenge.presentation.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.asimqasimzade.takeawaychallenge.domain.SchedulerProvider
import com.asimqasimzade.takeawaychallenge.presentation.home.HomeViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<T: ViewModel, B: ViewDataBinding> : DaggerAppCompatActivity(), BaseView<T> {

    @Inject
    protected lateinit var schedulers: SchedulerProvider

    protected lateinit var binding: B
    protected val subscriptions = CompositeDisposable()
    protected lateinit var vmFactory: ViewModelProviderFactory<HomeViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, bindingLayout)
        vmFactory = ViewModelProviderFactory(HomeViewModel(application, schedulers))
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }
}