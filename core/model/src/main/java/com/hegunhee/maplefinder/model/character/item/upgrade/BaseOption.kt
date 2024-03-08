package com.hegunhee.maplefinder.model.character.item.upgrade

import com.hegunhee.maplefinder.model.character.item.ItemOption

data class BaseOption(
    val options : List<ItemOption>,
    val baseLevel : Int
)