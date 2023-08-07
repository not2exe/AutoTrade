package com.autotrade.searchscreenfeature.ui

import com.autotrade.common.carcommunication.CarDomain

data class CarVo(
    val fullName: String,
    val price: String,
    val mileage: String,
    val color: String,
    val engineCharacteristics: String,
    val drive: String,
    val body: String,
    val countOwners: String,
    val condition: String,
    val carDomain: CarDomain
)