package com.autotrade.fullscreencarfeature.domain

import com.autotrade.common.carcommunication.CarFields
import com.autotrade.fullscreencarfeature.R
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RequestCarMapper @Inject constructor() {

    fun map(list: List<Pair<Int, String>>): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        val secondMap = mutableMapOf<String, Any>()
        list.forEach { element ->
            when (element.first) {
                R.string.brand -> map[CarFields.BRAND.string] = element.second
                R.string.model -> map[CarFields.MODEL.string] = element.second
                R.string.body -> map[CarFields.BODY.string] = element.second
                R.string.color -> map[CarFields.COLOR.string] = element.second

                R.string.condition -> map[CarFields.CONDITION.string] = element.second
                R.string.countOwners -> map[CarFields.COUNT_OWNERS.string] = element.second.toInt()
                R.string.wheel -> map[CarFields.IS_RIGHT_WHEEL.string] =
                    (element.second == "Правосторонний")

                R.string.year -> map[CarFields.YEAR.string] = element.second.toInt()
                R.string.drive -> map[CarFields.DRIVE.string] = element.second
                R.string.mileage -> map[CarFields.MILEAGE.string] = element.second.toInt()
                R.string.price -> map[CarFields.PRICE.string] = element.second.toInt()
                R.string.engineCapacity ->
                    secondMap[CarFields.CAPACITY.string] = element.second.toDouble()

                R.string.enginePower ->
                    secondMap[CarFields.POWER.string] = element.second.toInt()

                R.string.engineType -> secondMap[CarFields.TYPE.string] = element.second

                else -> {}

            }
        }
        map[CarFields.ENGINE.string] = secondMap
        return map
    }
}
