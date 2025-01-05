package com.hegunhee.maplefinder.model.character.item.normal

import com.hegunhee.maplefinder.model.ImageUrl

data class Title(
    val dateExpire : String,
    val dateOptionExpire : String,
    val description : String,
    val icon : ImageUrl,
    val name : String
)
