package com.hegunhee.maplefinder.data.api.model.character.item.cash

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CashItemResponse(
    @SerialName("character_class") val characterClass: String,
    @SerialName("character_gender") val characterGender: String,
    @SerialName("character_look_mode") val characterLookMode: String?,
    @SerialName("preset_no") val presetNo: Int?,
    @SerialName("cash_item_equipment_base") val equipmentBase: List<CashEquipmentItemResponse>,
    @SerialName("cash_item_equipment_preset_1") val preset1: List<CashEquipmentItemResponse>,
    @SerialName("cash_item_equipment_preset_2") val preset2: List<CashEquipmentItemResponse>,
    @SerialName("cash_item_equipment_preset_3") val preset3: List<CashEquipmentItemResponse>,
    @SerialName("additional_cash_item_equipment_base") val additionalBase: List<CashEquipmentItemResponse>,
    @SerialName("additional_cash_item_equipment_preset_1") val additionalPreset1: List<CashEquipmentItemResponse>,
    @SerialName("additional_cash_item_equipment_preset_2") val additionalPreset2: List<CashEquipmentItemResponse>,
    @SerialName("additional_cash_item_equipment_preset_3") val additionalPreset3: List<CashEquipmentItemResponse>,
    @SerialName("date") val date: String?,
)
