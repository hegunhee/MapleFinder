package com.hegunhee.maplefinder.data.api.model.character.stat

import com.hegunhee.maplefinder.model.character.DetailStat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailStat(
    @SerialName("stat_name")val statName: String,
    @SerialName("stat_value")val statValue: String
) {
    fun toModel() : DetailStat {
        return DetailStat(
            statName = statName,
            statValue = statValue
        )
    }
}