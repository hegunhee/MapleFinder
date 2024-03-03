package com.hegunhee.maplefinder.data.api.model.character.stat

import com.hegunhee.maplefinder.data.mapper.toGrade
import com.hegunhee.maplefinder.model.character.AbilityPreset
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityPreset(
    @SerialName("ability_info")val abilityInfo: List<AbilityInfo>,
    @SerialName("ability_preset_grade")val abilityGrade: String
) {

    fun toModel() : AbilityPreset {
        return AbilityPreset(
            abilityInfo = abilityInfo.toModel(),
            abilityGrade = abilityGrade.toGrade()
        )
    }
}