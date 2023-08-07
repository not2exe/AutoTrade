package com.autotrade.fullscreencarfeature.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import com.autotrade.common.isDouble
import com.autotrade.common.isInt
import com.autotrade.fullscreencarfeature.R

data class CarRedactVo(
    val id: String,
    val entries: List<EditText>
) {

    constructor() : this(
        "", listOf(
            EditText(R.string.brand, ""),
            EditText(R.string.model, ""),
            EditText(R.string.body, ""),
            EditText(R.string.color, ""),
            EditText(R.string.condition, ""),
            EditText(R.string.countOwners, "", KeyboardType.Number) { it.isInt() },
            EditText(R.string.wheel, ""),
            EditText(R.string.year, "", KeyboardType.Number) { it.isInt() },
            EditText(R.string.drive, ""),
            EditText(R.string.mileage, "", KeyboardType.Number) { it.isInt() },
            EditText(R.string.price, "", KeyboardType.Number) { it.isInt() },
            EditText(R.string.engineCapacity, "", KeyboardType.Decimal) { it.isDouble() },
            EditText(R.string.enginePower, "", KeyboardType.Number) { it.isInt() },
            EditText(R.string.engineType, "")
        )
    )

    class EditText(
        val labelStringId: Int,
        text: String,
        val keyboardType: KeyboardType = KeyboardType.Text,
        val condition: (_: String) -> Boolean = { true }
    ) {
        val mutableStateText = mutableStateOf(text)
    }

}

