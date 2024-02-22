package com.hegunhee.maplefinder.model.character

import com.hegunhee.maplefinder.model.ImageUrl

data class Character(
    val basic : CharacterBasic,
    val stat : CharacterStat,
    val hyperStat : CharacterHyperStat,
    val ability : CharacterAbility,
    val name : String = basic.name,
    val job : String = basic.jobName,
    val worldName : String = basic.worldName,
    val image : ImageUrl = basic.image
)