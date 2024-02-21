package com.hegunhee.maplefinder.model.character

data class CharacterStat(
    val jobName : String,
    val detailStatList : List<DetailStat>,
    val remainAp : Int,
    val date : String
)

data class DetailStat(
    val statName : String,
    val statValue : String
)
