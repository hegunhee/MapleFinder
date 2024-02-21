package com.hegunhee.maplefinder.data.api.model.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//{
//  "date": "2023-12-22T00:00+09:00",
//  "character_class": "엔젤릭버스터",
//  "world_name": "스카니아",
//  "dojang_best_floor": 46,
//  "date_dojang_record": "2023-02-27T00:00+09:00",
//  "dojang_best_time": 741
//}
@JsonClass(generateAdapter = true)
data class CharacterDojangResponse(
    @Json(name = "character_class") val characterClass: String,
    @Json(name = "date") val date: String,
    @Json(name = "date_dojang_record") val dojangRecordDate: String,
    @Json(name = "dojang_best_floor") val dojangBestFloor: Int,
    @Json(name = "dojang_best_time") val dojangBestTime: Int,
    @Json(name = "world_name") val worldName: String
)