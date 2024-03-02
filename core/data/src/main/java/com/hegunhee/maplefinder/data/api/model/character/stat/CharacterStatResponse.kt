package com.hegunhee.maplefinder.data.api.model.character.stat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterStatResponse(
    @Json(name = "character_class")val jobName: String,
    @Json(name = "date")val date: String,
    @Json(name = "final_stat")val detailStatList: List<DetailStat>,
    @Json(name = "remain_ap")val remainAp: Int
)