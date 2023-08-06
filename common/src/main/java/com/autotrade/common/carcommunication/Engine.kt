package com.autotrade.common.carcommunication

import com.autotrade.common.capitalize

data class Engine(
    val capacity: Double,
    val power: Int,
    val type: String,
) {
    override fun toString(): String {
        return "$capacity л./$power л.с./${
            type.capitalize()
        }"
    }
}
