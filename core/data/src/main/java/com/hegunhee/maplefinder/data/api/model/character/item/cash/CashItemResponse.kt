package com.hegunhee.maplefinder.data.api.model.character.item.cash

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CashItemResponse(
    @SerialName("character_class") val characterClass: String,
    @SerialName("character_gender") val characterGender: String,
    @SerialName("character_look_mode") val characterLookMode: String?,
    @SerialName("preset_no") val presetNo: Int?,
    @SerialName("cash_item_equipment_base") val equipmentBase: List<CashEquipmentItem>,
    @SerialName("cash_item_equipment_preset_1") val preset1: List<CashEquipmentItem>,
    @SerialName("cash_item_equipment_preset_2") val preset2: List<CashEquipmentItem>,
    @SerialName("cash_item_equipment_preset_3") val preset3: List<CashEquipmentItem>,
    @SerialName("additional_cash_item_equipment_base") val additionalBase: List<CashEquipmentItem>,
    @SerialName("additional_cash_item_equipment_preset_1") val additionalPreset1: List<CashEquipmentItem>,
    @SerialName("additional_cash_item_equipment_preset_2") val additionalPreset2: List<CashEquipmentItem>,
    @SerialName("additional_cash_item_equipment_preset_3") val additionalPreset3: List<CashEquipmentItem>,
    @SerialName("date") val date: String?,
)
