package com.autotrade.fullscreencarfeature.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
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
            EditText(R.string.countOwners, "", KeyboardType.Number),
            EditText(R.string.wheel, ""),
            EditText(R.string.year, "", KeyboardType.Number),
            EditText(R.string.drive, ""),
            EditText(R.string.mileage, "", KeyboardType.Number),
            EditText(R.string.price, "", KeyboardType.Number),
            EditText(R.string.engineCapacity, "", KeyboardType.Decimal),
            EditText(R.string.enginePower, "", KeyboardType.Number),
            EditText(R.string.engineType, "")
        )
    )

    class EditText(
        val labelStringId: Int,
        text: String,
        val keyboardType: KeyboardType = KeyboardType.Text
    ) {
        val mutableStateText = mutableStateOf(text)
    }

}

