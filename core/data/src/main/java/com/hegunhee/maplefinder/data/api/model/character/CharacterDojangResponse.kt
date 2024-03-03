package com.hegunhee.maplefinder.data.api.model.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//{
//  "date": "2023-12-22T00:00+09:00",
//  "character_class": "엔젤릭버스터",
//  "world_name": "스카니아",
//  "dojang_best_floor": 46,
//  "date_dojang_record": "2023-02-27T00:00+09:00",
//  "dojang_best_time": 741
//}
@Serializable
data class CharacterDojangResponse(
    @SerialName("character_class") val characterClass: String,
    @SerialName("date") val date: String,
    @SerialName("date_dojang_record") val dojangRecordDate: String,
    @SerialName("dojang_best_floor") val dojangBestFloor: Int,
    @SerialName("dojang_best_time") val dojangBestTime: Int,
    @SerialName("world_name") val worldName: String
)