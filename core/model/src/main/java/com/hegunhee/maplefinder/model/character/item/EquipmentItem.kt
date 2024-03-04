package com.hegunhee.maplefinder.model.character.item

data class EquipmentItem(
    val jobName : String,
    val gender : String,
    val itemList : List<Item>,
    val dragonItem : List<Item>,
    val mechanicItem : List<Item>,
    val title : Title,
    val date : String
)
