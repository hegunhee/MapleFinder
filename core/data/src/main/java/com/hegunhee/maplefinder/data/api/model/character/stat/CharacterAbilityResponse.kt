package com.hegunhee.maplefinder.data.api.model.character.stat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterAbilityResponse(
    @Json(name = "ability_grade")val abilityGrade: String,
    @Json(name = "ability_info")val abilityInfo: List<AbilityInfo>,
    @Json(name = "ability_preset_1")val abilityPreset1: AbilityPreset?,
    @Json(name = "ability_preset_2")val abilityPreset2: AbilityPreset?,
    @Json(name = "ability_preset_3")val abilityPreset3: AbilityPreset?,
    @Json(name = "date")val date: String,
    @Json(name = "preset_no")val presetNo: Int?,
    @Json(name = "remain_fame")val remainFame: Int
)