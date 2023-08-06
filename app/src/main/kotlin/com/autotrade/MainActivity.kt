package com.autotrade

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.autotrade.di.AppComponentHolder
import com.autotrade.searchscreenfeature.di.SearchScreenFeatureComponent
import com.autotrade.searchscreenfeature.di.SearchScreenFeatureComponentProvider

class MainActivity : FragmentActivity(), SearchScreenFeatureComponentProvider {

    private val activityComponent by lazy {
        (application as AppComponentHolder).component.mainActivityComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun provideComponent(): SearchScreenFeatureComponent =
        activityComponent.getSearchScreenFeatureComponent().create()
}