package com.hegunhee.maplefinder.model.character

data class CharacterAbility(
    val abilityGrade : String,
    val abilityInfo : List<AbilityInfo>,
    val abilityPreset1 : AbilityPreset?,
    val abilityPreset2 : AbilityPreset?,
    val abilityPreset3 : AbilityPreset?,
    val presetNo : Int?,
    val remainFame : Int
)

data class AbilityInfo(
    val grade : String,
    val num : String,
    val value : String
)

data class AbilityPreset(
    val abilityInfo : List<AbilityInfo>,
    val abilityGrade : String
)