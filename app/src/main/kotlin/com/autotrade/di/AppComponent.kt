package com.autotrade.di

import android.content.Context
import com.autotrade.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = []
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainActivityComponent(): MainActivityComponent
}