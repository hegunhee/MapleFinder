package com.hegunhee.maplefinder.model.character.item.upgrade

import com.hegunhee.maplefinder.model.Grade

data class CubeOption(
    val grade : Grade,
    val firstOption : String,
    val secondOption : String,
    val thirdOption : String
)
