package com.hegunhee.maplefinder.model.character

import com.hegunhee.maplefinder.model.Grade

data class CharacterAbility(
    val abilityGrade : Grade,
    val abilityInfo : List<AbilityInfo>,
    val abilityPreset1 : AbilityPreset?,
    val abilityPreset2 : AbilityPreset?,
    val abilityPreset3 : AbilityPreset?,
    val presetNo : Int?,
    val remainFame : Int
)

data class AbilityInfo(
    val grade : Grade,
    val num : String,
    val value : String
)

data class AbilityPreset(
    val abilityInfo : List<AbilityInfo>,
    val abilityGrade : Grade
)