package com.hegunhee.maplefinder.data.api.model.character.item.cash

import com.hegunhee.maplefinder.model.ImageUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CashEquipmentItemResponse(
    @SerialName("cash_item_coloring_prism") val coloringPrism: CashItemColoringPrismResponse?,
    @SerialName("cash_item_description") val description: String = "",
    @SerialName("cash_item_equipment_part") val part: String,
    @SerialName("cash_item_equipment_slot") val slot: String,
    @SerialName("cash_item_icon") val icon: ImageUrl,
    @SerialName("cash_item_label") val label: String?,
    @SerialName("cash_item_name") val name: String,
    @SerialName("cash_item_option") val option: List<CashItemOptionResponse>,
    @SerialName("date_expire") val dateExpire: String?,
    @SerialName("date_option_expire") val dateOptionExpire: String?,
    @SerialName("item_gender") val itemGender: String = "무관",
)
