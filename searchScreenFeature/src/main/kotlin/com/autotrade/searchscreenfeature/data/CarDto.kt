package com.autotrade.searchscreenfeature.data

import com.google.firebase.firestore.DocumentId

data class CarDto(
    @DocumentId
    val id: String,
    val body: String?,
    val brand: String?,
    val color: String?,
    val condition: String?,
    val countOwners: Int?,
    val engine: Map<String, Any>?,
    @JvmField
    val isRightWheel: Boolean?,
    val mileage: Int?,
    val model: String?,
    val price: Int?,
    val year: Int?,
    val drive: String?,
) {
    constructor() : this(
        "",
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}