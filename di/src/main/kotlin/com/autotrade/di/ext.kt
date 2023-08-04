package com.autotrade.di

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.autotrade.di.factory.ViewModelsFactory

inline fun <reified T : ViewModel> Fragment.injectedViewModel(
    noinline viewModelFactory: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
    ViewModelsFactory(this, viewModelFactory)
}

inline fun <reified T : ViewModel> ComponentActivity.injectedViewModel(
    noinline viewModelFactory: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
    ViewModelsFactory(this, viewModelFactory)
}