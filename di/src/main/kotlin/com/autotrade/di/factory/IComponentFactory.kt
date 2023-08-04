package com.autotrade.di.factory

interface IComponentFactory<out T> {
    fun create(): T
}
