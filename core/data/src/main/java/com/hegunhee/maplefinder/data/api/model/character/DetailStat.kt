package com.hegunhee.maplefinder.data.api.model.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailStat(
    @Json(name = "stat_name")val statName: String,
    @Json(name = "stat_value")val statValue: String
)