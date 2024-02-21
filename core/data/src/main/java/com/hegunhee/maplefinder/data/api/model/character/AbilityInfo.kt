package com.hegunhee.maplefinder.data.api.model.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityInfo(
    @Json(name = "ability_grade")val grade: String,
    @Json(name = "ability_no")val num: String,
    @Json(name = "ability_value")val value: String
)