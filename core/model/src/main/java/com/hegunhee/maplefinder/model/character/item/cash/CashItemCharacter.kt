package com.hegunhee.maplefinder.model.character.item.cash

import com.hegunhee.maplefinder.model.ImageUrl

data class CashItemCharacter(
    val name: String,
    val characterClass: String,
    val characterGender: String,
    val characterLookMode: LookupMode,
    val characterIcon: ImageUrl,
    val presetNo: Int?,
    val equipmentBase: List<CashEquipmentItem>,
    val equipmentPreset: List<List<CashEquipmentItem>>,
    val additionalBase: List<CashEquipmentItem>,
    val additionalPreset: List<List<CashEquipmentItem>>,
    val date: String?,
)
