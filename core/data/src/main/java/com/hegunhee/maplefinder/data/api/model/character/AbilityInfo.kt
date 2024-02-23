package com.hegunhee.maplefinder.data.api.model.character

import com.hegunhee.maplefinder.data.mapper.toGrade
import com.hegunhee.maplefinder.model.character.AbilityInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityInfo(
    @Json(name = "ability_grade")val grade: String,
    @Json(name = "ability_no")val num: String,
    @Json(name = "ability_value")val value: String
) {
    fun toModel() : AbilityInfo {
        return AbilityInfo(
            grade = grade.toGrade(),
            num = num,
            value = value
        )
    }
}

fun List<com.hegunhee.maplefinder.data.api.model.character.AbilityInfo>.toModel() : List<AbilityInfo> {
    return map(com.hegunhee.maplefinder.data.api.model.character.AbilityInfo::toModel)
}