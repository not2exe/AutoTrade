package com.autotrade.di

import com.autotrade.di.scopes.ActivityScope
import com.autotrade.fullscreencarfeature.di.FullScreenCarFeatureComponent
import com.autotrade.searchscreenfeature.di.SearchScreenFeatureComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SubcomponentsModule::class])
interface MainActivityComponent {
    fun getSearchScreenFeatureComponent(): SearchScreenFeatureComponent.Factory

    fun getFullScreenFeatureComponent(): FullScreenCarFeatureComponent.Factory
}