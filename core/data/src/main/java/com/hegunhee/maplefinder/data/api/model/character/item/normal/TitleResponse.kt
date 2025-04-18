package com.hegunhee.maplefinder.data.api.model.character.item.normal

import com.hegunhee.maplefinder.model.ImageUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitleResponse(
    @SerialName("date_expire")val dateExpire: String = "",
    @SerialName("date_option_expire")val dateOptionExpire: String = "",
    @SerialName("title_description")val description: String,
    @SerialName("title_icon")val icon: ImageUrl,
    @SerialName("title_name")val name: String
) {

    companion object {
        val EMPTY = TitleResponse(dateExpire = "", dateOptionExpire = "", description = "",icon = "",name = "")
    }
}