package com.hegunhee.maplefinder.data.api.model.character.stat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterHyperStatResponse(
    @Json(name = "character_class") val jobName: String,
    @Json(name = "date") val date: String,
    @Json(name = "hyper_stat_preset_1") val hyperStatPreset1: List<HyperStat>,
    @Json(name = "hyper_stat_preset_1_remain_point") val hyperStatPreset1RemainPoint: Int,
    @Json(name = "hyper_stat_preset_2") val hyperStatPreset2: List<HyperStat>,
    @Json(name = "hyper_stat_preset_2_remain_point") val hyperStatPreset2RemainPoint: Int,
    @Json(name = "hyper_stat_preset_3") val hyperStatPreset3: List<HyperStat>,
    @Json(name = "hyper_stat_preset_3_remain_point") val hyperStatPreset3RemainPoint: Int,
    @Json(name = "use_available_hyper_stat") val remainHyperStat: Int,
    @Json(name = "use_preset_no") val currentPresetNum: String
)