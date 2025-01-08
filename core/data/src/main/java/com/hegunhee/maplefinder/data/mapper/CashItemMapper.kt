package com.hegunhee.maplefinder.data.mapper

import com.hegunhee.maplefinder.data.api.model.character.item.cash.CashEquipmentItemResponse
import com.hegunhee.maplefinder.data.api.model.character.item.cash.CashItemColoringPrismResponse
import com.hegunhee.maplefinder.data.api.model.character.item.cash.CashItemOptionResponse
import com.hegunhee.maplefinder.data.api.model.character.item.cash.CashItemResponse
import com.hegunhee.maplefinder.model.character.CharacterBasic
import com.hegunhee.maplefinder.model.character.item.cash.CashEquipmentItem
import com.hegunhee.maplefinder.model.character.item.cash.CashItemCharacter
import com.hegunhee.maplefinder.model.character.item.cash.CashItemColoringPrism
import com.hegunhee.maplefinder.model.character.item.cash.CashItemOption
import com.hegunhee.maplefinder.model.character.item.cash.LookupMode

fun CashItemResponse.toModel(characterInfo: CharacterBasic): CashItemCharacter {
    return CashItemCharacter(
        name = characterInfo.name,
        characterClass = this.characterClass,
        characterGender = characterInfo.gender,
        characterLookMode = this.characterLookMode.toCharacterLookupMode(),
        characterIcon = characterInfo.image,
        presetNo = presetNo?.minus(1),
        equipmentBase = this.equipmentBase.toCashEquipmentItems(),
        equipmentPreset = listOf(
            this.preset1.toCashEquipmentItems(),
            this.preset2.toCashEquipmentItems(),
            this.preset3.toCashEquipmentItems()
        ),
        additionalBase = this.additionalBase.toCashEquipmentItems(),
        additionalPreset = listOf(
            this.additionalPreset1.toCashEquipmentItems(),
            this.additionalPreset2.toCashEquipmentItems(),
            this.additionalPreset3.toCashEquipmentItems()
        ),
        date = date
    )
}

private fun String?.toCharacterLookupMode(): LookupMode {
    return if (this == null || this == "0") {
        LookupMode.NORMAL
    } else {
        LookupMode.SPECIAL
    }
}

private fun List<CashEquipmentItemResponse>.toCashEquipmentItems(): List<CashEquipmentItem> {
    return this.map { it.toModel() }
}

private fun CashEquipmentItemResponse.toModel(): CashEquipmentItem {
    return CashEquipmentItem(
        name = name,
        part = part,
        slot = slot,
        icon = icon,
        label = label,
        option = option.toCashItemOptions(),
        coloringPrism = coloringPrism.toModel(),
        dateExpire = dateExpire,
        dateOptionExpire = dateOptionExpire,
        itemGender = itemGender,
    )
}

private fun List<CashItemOptionResponse>.toCashItemOptions(): List<CashItemOption> {
    return this.map { it.toModel() }
}

private fun CashItemOptionResponse.toModel(): CashItemOption {
    return CashItemOption(optionType, optionValue)
}

private fun CashItemColoringPrismResponse?.toModel(): CashItemColoringPrism? {
    return if (this != null) {
        CashItemColoringPrism(
            colorRange = colorRange,
            hue = hue,
            saturation = saturation,
            value = value,
        )
    } else {
        null
    }
}
