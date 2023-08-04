package com.autotrade.di

import android.app.Application

class AutoTradeApp : Application(),
    AppComponentHolder {

    override val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}