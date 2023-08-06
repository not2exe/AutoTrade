package com.autotrade.di

import com.autotrade.fullscreencarfeature.di.FullScreenCarFeatureComponent
import com.autotrade.searchscreenfeature.di.SearchScreenFeatureComponent
import dagger.Module

@Module(subcomponents = [SearchScreenFeatureComponent::class, FullScreenCarFeatureComponent::class])
class SubcomponentsModule