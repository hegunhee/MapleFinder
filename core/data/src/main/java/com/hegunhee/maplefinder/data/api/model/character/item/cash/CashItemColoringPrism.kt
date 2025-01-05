package com.hegunhee.maplefinder.data.api.model.character.item.cash

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CashItemColoringPrism(
    @SerialName("color_range") val colorRange: String,
    @SerialName("hue") val hue: Int,
    @SerialName("saturation") val saturation: Int,
    @SerialName("value") val value: Int,
)
