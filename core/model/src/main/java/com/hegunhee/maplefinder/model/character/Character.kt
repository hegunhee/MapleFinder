package com.hegunhee.maplefinder.model.character

import com.hegunhee.maplefinder.model.ImageUrl
import com.hegunhee.maplefinder.model.World

data class Character(
    val basic : CharacterBasic,
    val stat : CharacterStat,
    val hyperStat : CharacterHyperStat,
    val ability : CharacterAbility,
    val name : String = basic.name,
    val job : String = basic.jobName,
    val world : World = basic.world,
    val image : ImageUrl = basic.image
)