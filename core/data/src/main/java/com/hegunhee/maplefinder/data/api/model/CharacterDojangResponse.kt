package com.hegunhee.maplefinder.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDojangResponse(
    @Json(name = "character_class") val characterClass: String,
    @Json(name = "date") val date: String,
    @Json(name = "date_dojang_record") val dojangRecordDate: String,
    @Json(name = "dojang_best_floor") val dojangBestFloor: Int,
    @Json(name = "dojang_best_time") val dojangBestTime: Int,
    @Json(name = "world_name") val worldName: String
)