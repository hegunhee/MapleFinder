package com.hegunhee.maplefinder.model.character.item

import com.hegunhee.maplefinder.model.ImageUrl
import com.hegunhee.maplefinder.model.World
import com.hegunhee.maplefinder.model.character.CharacterBasic

data class CharacterEquipmentItem(
    val ocid : String,
    val mainStat : String,
    val basic : CharacterBasic,
    val equipmentItem : EquipmentItem,
    val name : String = basic.name,
    val job : String = basic.jobName,
    val world : World = basic.world,
    val image : ImageUrl = basic.image,
)
