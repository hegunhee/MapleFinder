package com.hegunhee.maplefinder.model.character

import com.hegunhee.maplefinder.model.ImageUrl
import com.hegunhee.maplefinder.model.World

data class CharacterBasic(
    val name : String,
    val level : Int,
    val jobName : String,
    val jobLevel : String,
    val world : World,
    val image : ImageUrl,
    val gender : String,
    val exp : Long,
    val expRate : String,
    val guildName : String?,
    val date : String,
)
