package com.hegunhee.maplefinder.model.character.item.cash

import com.hegunhee.maplefinder.model.ImageUrl

data class CashEquipmentItem(
    val name: String,
    val part: String,
    val slot: String,
    val icon: ImageUrl,
    val label: String?,
    val option: List<CashItemOption>,
    val coloringPrism: CashItemColoringPrism?,
    val dateExpire: String?,
    val dateOptionExpire: String?,
    val itemGender: String,
) {

    fun isOptionEmpty() : Boolean {
        return option.isEmpty()
    }

}
