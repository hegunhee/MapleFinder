package com.hegunhee.maplefinder.data.api.model.character.stat

import com.hegunhee.maplefinder.model.character.DetailStat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailStat(
    @Json(name = "stat_name")val statName: String,
    @Json(name = "stat_value")val statValue: String
) {
    fun toModel() : DetailStat {
        return DetailStat(
            statName = statName,
            statValue = statValue
        )
    }
}