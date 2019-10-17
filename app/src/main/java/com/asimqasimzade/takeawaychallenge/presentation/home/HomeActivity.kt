package com.asimqasimzade.takeawaychallenge.presentation.home

import androidx.lifecycle.ViewModelProviders
import com.asimqasimzade.takeawaychallenge.R
import com.asimqasimzade.takeawaychallenge.databinding.ActivityHomeBinding
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseActivity


class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {
    override val bindingLayout = R.layout.activity_home
    override val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, vmFactory).get(HomeViewModel::class.java)
    }
}