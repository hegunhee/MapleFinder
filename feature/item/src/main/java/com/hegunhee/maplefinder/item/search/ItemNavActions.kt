package com.hegunhee.maplefinder.item.search

sealed interface ItemNavActions {

    data class Detail(
        val ocid: String,
        val date: String,
    ) : ItemNavActions

}
