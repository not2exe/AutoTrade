package com.autotrade.searchscreenfeature.domain

data class CarDomain(
    val body: String,
    val brand: String,
    val color: String,
    val condition: String,
    val countOwners: Int,
    val engine: Engine,
    val isRightWheel: Boolean,
    val mileage: Int,
    val model: String,
    val price: Int,
    val year: Int,
    val drive: String,
)