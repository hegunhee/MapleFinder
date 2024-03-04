package com.hegunhee.maplefinder.model.character.stat

data class CharacterStat(
    val jobName : String,
    val powerLevel : String,
    val detailStatList : List<DetailStat>,
    val remainAp : Int,
    val date : String
)

data class DetailStat(
    val statName : String,
    val statValue : String
)
