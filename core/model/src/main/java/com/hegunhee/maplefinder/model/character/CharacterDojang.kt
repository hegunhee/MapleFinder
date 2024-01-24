package com.hegunhee.maplefinder.model.character

data class CharacterDojang(
    val characterName : String,
    val characterClass : String,
    val worldName : String,
    val recordDate : String,
    val bestFloor : Int,
    val bestTimeStamp : String,
    val bestTime : Int,
)
