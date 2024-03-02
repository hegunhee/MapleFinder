package com.hegunhee.maplefinder.data.api.model.character.stat

import com.hegunhee.maplefinder.data.mapper.toGrade
import com.hegunhee.maplefinder.model.character.AbilityPreset
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityPreset(
    @Json(name = "ability_info")val abilityInfo: List<AbilityInfo>,
    @Json(name = "ability_preset_grade")val abilityGrade: String
) {

    fun toModel() : AbilityPreset {
        return AbilityPreset(
            abilityInfo = abilityInfo.toModel(),
            abilityGrade = abilityGrade.toGrade()
        )
    }
}