package com.autotrade.fullscreencarfeature.di

import com.autotrade.di.scopes.FragmentScope
import com.autotrade.fullscreencarfeature.ui.stateholders.CarRedactViewModel
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FullScreenCarFeatureModule::class])
interface FullScreenCarFeatureComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FullScreenCarFeatureComponent
    }

    fun viewModelFactory(): CarRedactViewModel.Factory
}