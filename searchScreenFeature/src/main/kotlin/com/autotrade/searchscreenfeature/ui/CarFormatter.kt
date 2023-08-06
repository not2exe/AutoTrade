package com.autotrade.searchscreenfeature.ui

import com.autotrade.common.CarVo
import com.autotrade.common.capitalize
import com.autotrade.searchscreenfeature.domain.CarDomain
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CarFormatter @Inject constructor() {
    fun format(carDomain: CarDomain): CarVo {
        return CarVo(
            fullName = "${carDomain.brand.capitalize()} ${carDomain.model.capitalize()}, ${carDomain.year}",
            body = carDomain.body.capitalize(),
            color = carDomain.color.capitalize(),
            price = "${formatNumber(carDomain.price)} ₽",
            mileage = "${formatNumber(carDomain.mileage)} км",
            engineCharacteristics = carDomain.engine.toString(),
            drive = carDomain.drive.capitalize(),
            countOwners = carDomain.countOwners.toString(),
            condition = carDomain.condition.capitalize()
        )
    }

    private fun formatNumber(number: Int): String {
        val strBuilder = StringBuilder()
        val list = number.toString()
            .reversed()
            .chunked(3).map { str ->
                str.reversed()
            }.reversed()
        for (i in list.indices) {
            strBuilder.append(list[i])
            if (i != list.size - 1) {
                strBuilder.append(" ")
            }
        }
        return strBuilder.toString()
    }
}
