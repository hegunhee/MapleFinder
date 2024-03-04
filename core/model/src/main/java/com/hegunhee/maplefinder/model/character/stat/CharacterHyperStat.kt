package com.hegunhee.maplefinder.model.character.stat

data class CharacterHyperStat(
    val jobName : String,
    val currentPreset : List<HyperStat>,
    val hyperStatPresetList : List<List<HyperStat>>,
    val remainHyperStat : Int,
    val currentPresetNum : String
)

data class HyperStat(
    val statIncrease : String?,
    val statLevel : Int,
    val statPoint : Int?,
    val statType : String
)
