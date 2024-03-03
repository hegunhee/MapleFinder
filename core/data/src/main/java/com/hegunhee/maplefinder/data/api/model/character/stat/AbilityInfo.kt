package com.hegunhee.maplefinder.data.api.model.character.stat

import com.hegunhee.maplefinder.data.mapper.toGrade
import com.hegunhee.maplefinder.model.character.AbilityInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityInfo(
    @SerialName("ability_grade")val grade: String,
    @SerialName("ability_no")val num: String,
    @SerialName("ability_value")val value: String
) {
    fun toModel() : AbilityInfo {
        return AbilityInfo(
            grade = grade.toGrade(),
            num = num,
            value = value
        )
    }
}

fun List<com.hegunhee.maplefinder.data.api.model.character.stat.AbilityInfo>.toModel() : List<AbilityInfo> {
    return map(com.hegunhee.maplefinder.data.api.model.character.stat.AbilityInfo::toModel)
}