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

internal fun ItemEquipmentResponse.toModel() : Item {
    val scrollOption = ScrollOption(scrollUpgradeCount,scrollUpgradeableCount,scrollRecoverableCount,goldenHammerFlag)
    val isStarforceItem = isStarforceItemSlot(slot)
    val starCountMax = starMaxCount(baseOption.baseLevel)
    val starforceOption = StarforceOption(isStarforceItem,starCountMax,starforce,starforceScrollFlag)
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
        ItemOption(key = optionKeyToKorean(it.key), value = it.value.toString().substring(1,it.value.toString().length-1))
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

internal fun starMaxCount(itemLevel : Int) : Int {
    return when(itemLevel) {
        in 138..300 -> 25
        in 128..137 -> 20
        in 118..127 -> 15
        in 108..117 -> 10
        in 95..107 -> 8
        else -> 5
    }
}
internal fun isStarforceItemSlot(slot : String) : Boolean = !hasNoStarforceItemSlotMap.contains(slot)

private val hasNoStarforceItemSlotMap : List<String> = listOf<String>(
    "보조무기",
    "훈장",
    "포켓 아이템",
    "뱃지",
    "엠블렘"
)

internal fun optionKeyToKorean(key : String) : String {
    return optionKoreanMap[key] ?: ""
}

private val optionKoreanMap = mapOf<String,String>(
    Pair("str","힘"),
    Pair("dex","덱스"),
    Pair("int","인트"),
    Pair("luk","럭"),
    Pair("max_hp","최대 HP"),
    Pair("max_mp","최대 MP"),
    Pair("attack_power","공격력"),
    Pair("magic_power","마력"),
    Pair("armor","방어력"),
    Pair("speed","이동속도"),
    Pair("jump","점프력"),
    Pair("boss_damage","보스 데미지 증가(%)"),
    Pair("ignore_monster_armor","몬스터 방어력 무시(%)"),
    Pair("all_stat","올스텟(%)"),
    Pair("damage","데미지(%)"),
    Pair("equipment_level_decrease","착용 레벨 감소"),
    Pair("max_hp_rate","최대 HP(%)"),
    Pair("max_mp_rate","최대 MP(%)"),
)