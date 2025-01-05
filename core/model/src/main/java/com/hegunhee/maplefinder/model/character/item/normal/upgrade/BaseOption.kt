package com.hegunhee.maplefinder.model.character.item.normal.upgrade

import com.hegunhee.maplefinder.model.character.item.normal.ItemOption

data class BaseOption(
    val options : List<ItemOption>,
    val baseLevel : Int
)