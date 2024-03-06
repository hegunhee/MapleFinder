package com.hegunhee.maplefinder.model.character.item

import com.hegunhee.maplefinder.model.ImageUrl
import com.hegunhee.maplefinder.model.character.item.upgrade.CubeOption
import com.hegunhee.maplefinder.model.character.item.upgrade.ScrollOption
import com.hegunhee.maplefinder.model.character.item.upgrade.StarforceOption

data class Item(
    val part : String,
    val slot : String,
    val description : String,
    val itemGender : String,
    val icon : ImageUrl,
    val name : String,
    val shapeIcon : ImageUrl,
    val shapeName : String,
    val scrollOption : ScrollOption,
    val cuttableCount : String,
    val starforceCountOption: StarforceOption,
    val dateExpire : String = "",
    val equipmentLevelIncrease : Int,
    val growthExp : Int,
    val growthLevel : Int,
    val totalOption : List<ItemOption>,
    val baseOption : List<ItemOption>,
    val addOption : List<ItemOption>,
    val etcOption : List<ItemOption>,
    val exceptionalOption : List<ItemOption>,
    val starforceOption : List<ItemOption>,
    val potentialOption : CubeOption,
    val additionalOption : CubeOption,
    val soulName : String,
    val soulOption : String,
    val specialRingLevel : Int
) {
    fun getItemStatGrade(mainStat : String) : String {
        if(addOption.isEmpty()) {
            return ""
        }
        if(isItemStatGradeExist()) {
            val statValue = addOption.find { it.key == mainStat}?.value?.toInt() ?: 0
            val attackValue = if(mainStat == "인트") {
                addOption.find { it.key == "마력" }
            } else {
                addOption.find { it.key == "공격력" }
            }?.value?.toInt() ?: 0
            val allStatValue = addOption.find { it.key == "올스텟(%)" }?.value?.toInt() ?: 0
            println()
            return (statValue + (attackValue * 4) + (allStatValue * 10)).toString()
        }
        return ""
    }

    private fun isItemStatGradeExist() : Boolean {
        return itemStatGradeParts.contains(part)
    }

    companion object {
        private val itemStatGradeParts = listOf<String>("얼굴장식","눈장식","펜던트","모자","상의","하의","신발","장갑","망토","벨트","어깨장식")
    }
}
