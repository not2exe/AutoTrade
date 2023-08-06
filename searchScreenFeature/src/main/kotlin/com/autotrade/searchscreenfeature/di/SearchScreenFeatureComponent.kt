package com.autotrade.searchscreenfeature.di

import com.autotrade.di.scopes.FragmentScope
import com.autotrade.searchscreenfeature.ui.stateholders.SearchCarViewModel
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SearchScreenFeatureModule::class])
interface SearchScreenFeatureComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchScreenFeatureComponent
    }

    fun viewModelFactory(): SearchCarViewModel.Factory
}