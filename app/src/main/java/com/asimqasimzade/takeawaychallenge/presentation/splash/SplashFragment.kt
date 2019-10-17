package com.asimqasimzade.takeawaychallenge.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.asimqasimzade.takeawaychallenge.R
import com.asimqasimzade.takeawaychallenge.databinding.FragmentSplashBinding
import com.asimqasimzade.takeawaychallenge.di.ViewModelProviderFactory
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseFragment

class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override val bindingLayout = R.layout.fragment_splash
    override val viewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this, vmFactory).get(SplashViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController(this).navigate(R.id.goToRestaurantsFromSplashAction)
    }
}
