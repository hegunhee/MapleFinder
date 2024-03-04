package com.hegunhee.maplefinder.data.mapper

import com.hegunhee.maplefinder.data.api.model.character.item.ItemEquipmentResponse
import com.hegunhee.maplefinder.data.api.model.character.item.ItemResponse
import com.hegunhee.maplefinder.data.api.model.character.item.TitleResponse
import com.hegunhee.maplefinder.data.api.model.character.item.option.ItemOptionResponse
import com.hegunhee.maplefinder.model.character.item.EquipmentItem
import com.hegunhee.maplefinder.model.character.item.Item
import com.hegunhee.maplefinder.model.character.item.ItemOption
import com.hegunhee.maplefinder.model.character.item.Title
import com.hegunhee.maplefinder.model.character.item.upgrade.CubeOption
import com.hegunhee.maplefinder.model.character.item.upgrade.ScrollOption
import com.hegunhee.maplefinder.model.character.item.upgrade.StarforceOption
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

internal fun ItemResponse.toModel() : EquipmentItem {
    return EquipmentItem(
        jobName = characterClass,
        gender = characterGender,
        itemList = itemEquipmentResponse.map { it.toModel() },
        dragonItem = dragonEquipment.map { it.toModel() },
        mechanicItem = mechanicEquipment.map { it.toModel() },
        title = titleResponse.toModel(),
        date = date
    )
}

private fun ItemEquipmentResponse.toModel() : Item {
    val scrollOption = ScrollOption(scrollUpgradeCount,scrollUpgradeableCount,scrollRecoverableCount,goldenHammerFlag)
    val starforceOption = StarforceOption(starforce,starforceScrollFlag)
    return Item(
        part = part,
        slot = slot,
        description = description,
        itemGender = itemGender,
        icon = icon,
        name = name,
        shapeIcon = shapeIcon,
        shapeName = shapeName,
        scrollOption = scrollOption,
        cuttableCount = cuttableCount,
        starforceCountOption = starforceOption,
        dateExpire = dateExpire,
        equipmentLevelIncrease = equipmentLevelIncrease,
        growthExp = growthExp,
        growthLevel = growthLevel,
        totalOption = totalOption.toModel(),
        baseOption = baseOption.toModel(),
        addOption = addOption.toModel(),
        etcOption = etcOption.toModel(),
        exceptionalOption = exceptionalOption.toModel(),
        starforceOption = this.starforceOption.toModel(),
        potentialOption = cubeOptionToModel(potentialOptionGrade,potentialOption1,potentialOption2,potentialOption3),
        additionalOption = cubeOptionToModel(additionalPotentialOptionGrade,additionalPotentialOption1,additionalPotentialOption2,additionalPotentialOption3),
        soulName = soulName,
        soulOption = soulOption,
        specialRingLevel = specialRingLevel

    )
}

private fun TitleResponse.toModel() : Title {
    return Title(
        dateExpire = dateExpire,
        dateOptionExpire = dateOptionExpire,
        description = description,
        icon = icon,
        name = name
    )
}

private fun ItemOptionResponse.toModel() : List<ItemOption>{
    val jsonOptionList = Json.encodeToString(this)
    val jsonObject = (Json.parseToJsonElement(jsonOptionList) as JsonObject)
    val itemOptionList = jsonObject.map {
        ItemOption(key = it.key, value = it.value.toString().substring(1,it.value.toString().length-1))
    }.toList()
    return itemOptionList
}

private fun cubeOptionToModel(grade : String,option1 : String,option2 :String, option3 : String) : CubeOption {
    return CubeOption(
        grade = grade.toGrade(),
        firstOption = option1,
        secondOption = option2,
        thirdOption = option3
    )
}