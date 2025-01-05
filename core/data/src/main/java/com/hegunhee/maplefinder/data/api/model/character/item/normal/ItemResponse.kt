package com.hegunhee.maplefinder.data.api.model.character.item.normal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * dragon_equipment = 에반의 드래곤 장비들
 * machanic_equipment = 메카닉의 기계 장비들
 * title = 칭호
 */

@Serializable
data class ItemResponse(
    @SerialName("character_class")val characterClass: String,
    @SerialName("character_gender")val characterGender: String,
    @SerialName("date")val date: String,
    @SerialName("dragon_equipment")val dragonEquipment: List<ItemEquipmentResponse> = emptyList(),
    @SerialName("item_equipment")val itemEquipmentResponse: List<ItemEquipmentResponse>,
    @SerialName("item_equipment_preset_1")val preset1: List<ItemEquipmentResponse> = emptyList(),
    @SerialName("item_equipment_preset_2")val preset2: List<ItemEquipmentResponse> = emptyList(),
    @SerialName("item_equipment_preset_3")val preset3: List<ItemEquipmentResponse> = emptyList(),
    @SerialName("mechanic_equipment")val mechanicEquipment: List<ItemEquipmentResponse> = emptyList(),
    @SerialName("preset_no")val presetNo: Int = 0,
    @SerialName("title") val titleResponse: TitleResponse = TitleResponse.EMPTY
)

