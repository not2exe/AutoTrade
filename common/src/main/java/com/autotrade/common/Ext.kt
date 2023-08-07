package com.autotrade.common

import java.util.Locale

fun String.capitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

fun String.isInt(): Boolean {
    return this.toIntOrNull() != null
}

fun String.isDouble(): Boolean {
    return this.toDoubleOrNull() != null
}