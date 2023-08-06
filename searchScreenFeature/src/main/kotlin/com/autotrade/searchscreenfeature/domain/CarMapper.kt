package com.autotrade.searchscreenfeature.domain

import com.autotrade.searchscreenfeature.data.CarDto
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CarMapper @Inject constructor() {
    fun map(carDto: CarDto): CarDomain =
        CarDomain(
            body = requireNotNull(carDto.body),
            brand = requireNotNull(carDto.brand),
            color = requireNotNull(carDto.color),
            condition = requireNotNull(carDto.condition),
            countOwners = requireNotNull(carDto.countOwners),
            engine = Engine(
                requireNotNull(carDto.engine?.get("capacity")).toString().toDouble(),
                requireNotNull(carDto.engine?.get("power")).toString().toInt(),
                requireNotNull(carDto.engine?.get("type").toString())
            ),
            drive = requireNotNull(carDto.drive),
            isRightWheel = requireNotNull(carDto.isRightWheel),
            mileage = requireNotNull(carDto.mileage),
            price = requireNotNull(carDto.price),
            year = requireNotNull(carDto.year),
            model = requireNotNull(carDto.model)
        )

}