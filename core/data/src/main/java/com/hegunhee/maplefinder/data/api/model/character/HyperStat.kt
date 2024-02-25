package com.hegunhee.maplefinder.data.api.model.character

import com.hegunhee.maplefinder.model.character.HyperStat
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
) {
    fun toModel() : HyperStat {
        val statSplit = statIncrease?.split(" ")
        return HyperStat(
            statIncrease = statSplit?.takeLast(2)?.get(0), // 스텟 증가량만 추출
            statLevel = statLevel,
            statPoint = statPoint,
            statType = statType
        )
    }
}

fun List<com.hegunhee.maplefinder.data.api.model.character.HyperStat>.toModel() : List<HyperStat> {
    return this.map(com.hegunhee.maplefinder.data.api.model.character.HyperStat::toModel)
}