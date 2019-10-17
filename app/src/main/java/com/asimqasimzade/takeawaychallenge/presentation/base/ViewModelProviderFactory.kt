package com.asimqasimzade.takeawaychallenge.presentation.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory<VMType : AndroidViewModel>(private val mViewModel: VMType) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(mViewModel::class.java)) {
            return mViewModel as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}