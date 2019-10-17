package com.asimqasimzade.takeawaychallenge.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.asimqasimzade.takeawaychallenge.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<T: BaseViewModel, B: ViewDataBinding> : DaggerFragment(), BaseView<T> {

    protected lateinit var binding: B
    protected val subscriptions = CompositeDisposable()

    @Inject
    protected lateinit var vmFactory: ViewModelProviderFactory<T>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, bindingLayout, container, false)
        return binding.root
    }

    override fun onPause() {
        subscriptions.clear()
        super.onPause()
    }
}