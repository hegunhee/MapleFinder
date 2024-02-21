package com.hegunhee.maplefinder.data.api.model.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityPreset(
    @Json(name = "ability_info")val abilityInfo: List<AbilityInfo>,
    @Json(name = "ability_preset_grade")val abilityGrade: String
)