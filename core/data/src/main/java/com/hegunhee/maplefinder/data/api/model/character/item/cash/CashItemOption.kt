package com.hegunhee.maplefinder.data.api.model.character.item.cash

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CashItemOption(
    @SerialName("option_type") val optionType: String,
    @SerialName("option_value") val optionValue: String
)
