package com.autotrade.fullscreencarfeature.ui

import androidx.compose.ui.text.input.KeyboardType
import com.autotrade.common.carcommunication.CarDomain
import com.autotrade.common.capitalize
import com.autotrade.fullscreencarfeature.R
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CarRedactFormatter @Inject constructor() {
    fun format(carDomain: CarDomain?): CarRedactVo =
        if (carDomain == null) {
            CarRedactVo()
        } else {
            CarRedactVo(
                id = carDomain.id,
                entries = listOf(
                    CarRedactVo.EditText(R.string.brand, carDomain.brand.capitalize()),
                    CarRedactVo.EditText(R.string.model, carDomain.model.capitalize()),
                    CarRedactVo.EditText(R.string.body, carDomain.body.capitalize()),
                    CarRedactVo.EditText(R.string.color, carDomain.color.capitalize()),
                    CarRedactVo.EditText(R.string.condition, carDomain.condition.capitalize()),
                    CarRedactVo.EditText(
                        R.string.countOwners, carDomain.countOwners.toString(),
                        KeyboardType.Number
                    ),
                    CarRedactVo.EditText(
                        R.string.wheel, if (carDomain.isRightWheel) {
                            "Правосторонний"
                        } else {
                            "Левосторонний"
                        }
                    ),
                    CarRedactVo.EditText(
                        R.string.year,
                        carDomain.year.toString(),
                        KeyboardType.Number
                    ),
                    CarRedactVo.EditText(R.string.drive, carDomain.drive.capitalize()),
                    CarRedactVo.EditText(
                        R.string.mileage,
                        carDomain.mileage.toString(),
                        KeyboardType.Number
                    ),
                    CarRedactVo.EditText(
                        R.string.price,
                        carDomain.price.toString(),
                        keyboardType = KeyboardType.Number
                    ),
                    CarRedactVo.EditText(
                        R.string.engineCapacity, carDomain.engine.capacity.toString(),
                        KeyboardType.Decimal
                    ),
                    CarRedactVo.EditText(
                        R.string.enginePower,
                        carDomain.engine.power.toString(),
                        KeyboardType.Number
                    ),
                    CarRedactVo.EditText(R.string.engineType, carDomain.engine.type.capitalize())
                )

            )
        }
}