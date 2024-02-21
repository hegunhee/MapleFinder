package com.hegunhee.maplefinder.data.api.model.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *       "stat_type": "크리티컬 확률",
 *       "stat_point": 60,
 *       "stat_level": 7,
 *       "stat_increase": "크리티컬 확률 9% 증가"
 */
@JsonClass(generateAdapter = true)
data class HyperStat(
    @Json(name = "stat_increase") val statIncrease: String?,
    @Json(name = "stat_level") val statLevel: Int,
    @Json(name = "stat_point") val statPoint: Int?,
    @Json(name = "stat_type") val statType: String
)