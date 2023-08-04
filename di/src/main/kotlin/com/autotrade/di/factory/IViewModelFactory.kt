package com.autotrade.di.factory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface IViewModelFactory<out TViewModel: ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): TViewModel
}