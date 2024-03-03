package com.hegunhee.maplefinder.data.api.model.character.stat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterAbilityResponse(
    @SerialName("ability_grade")val abilityGrade: String,
    @SerialName("ability_info")val abilityInfo: List<AbilityInfo>,
    @SerialName("ability_preset_1")val abilityPreset1: AbilityPreset?,
    @SerialName("ability_preset_2")val abilityPreset2: AbilityPreset?,
    @SerialName("ability_preset_3")val abilityPreset3: AbilityPreset?,
    @SerialName("date")val date: String,
    @SerialName("preset_no")val presetNo: Int?,
    @SerialName("remain_fame")val remainFame: Int
)