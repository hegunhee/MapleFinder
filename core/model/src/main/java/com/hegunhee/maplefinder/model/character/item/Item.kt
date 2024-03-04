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
)
