package com.autotrade

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.autotrade.di.AutoTradeApp
import com.autotrade.fullscreencarfeature.di.FullScreenCarFeatureComponent
import com.autotrade.fullscreencarfeature.di.FullScreenCarFeatureComponentProvider
import com.autotrade.searchscreenfeature.di.SearchScreenFeatureComponent
import com.autotrade.searchscreenfeature.di.SearchScreenFeatureComponentProvider

class MainActivity : FragmentActivity(), SearchScreenFeatureComponentProvider,
    FullScreenCarFeatureComponentProvider {

    private val activityComponent by lazy {
        (application as AutoTradeApp).component.mainActivityComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun provideFullScreenFeatureComponent(): FullScreenCarFeatureComponent =
        activityComponent.getFullScreenFeatureComponent().create()

    override fun provideSearchCarFeatureComponent(): SearchScreenFeatureComponent =
        activityComponent.getSearchScreenFeatureComponent().create()
}