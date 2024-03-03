package com.hegunhee.maplefinder.data.api.model.character.stat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterHyperStatResponse(
    @SerialName("character_class") val jobName: String,
    @SerialName("date") val date: String,
    @SerialName("hyper_stat_preset_1") val hyperStatPreset1: List<HyperStat>,
    @SerialName("hyper_stat_preset_1_remain_point") val hyperStatPreset1RemainPoint: Int,
    @SerialName("hyper_stat_preset_2") val hyperStatPreset2: List<HyperStat>,
    @SerialName("hyper_stat_preset_2_remain_point") val hyperStatPreset2RemainPoint: Int,
    @SerialName("hyper_stat_preset_3") val hyperStatPreset3: List<HyperStat>,
    @SerialName("hyper_stat_preset_3_remain_point") val hyperStatPreset3RemainPoint: Int,
    @SerialName("use_available_hyper_stat") val remainHyperStat: Int,
    @SerialName("use_preset_no") val currentPresetNum: String
)