package com.autotrade.di

import android.app.Application

class AutoTradeApp : Application(){

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}