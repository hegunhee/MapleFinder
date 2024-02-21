package com.hegunhee.maplefinder.model.character

import com.hegunhee.maplefinder.model.ImageUrl

data class CharacterBasic(
    val name : String,
    val jobName : String,
    val jobLevel : String,
    val worldName : String,
    val image : ImageUrl,
    val gender : String,
    val exp : Long,
    val expRate : String,
    val guildName : String?,
    val date : String,
)
