package com.hegunhee.maplefinder.data.api.model.character

import com.hegunhee.maplefinder.model.ImageUrl
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterBasicResponse(
    @Json(name = "character_class") val jobName: String,
    @Json(name = "character_class_level") val jobLevel: String,
    @Json(name = "character_exp") val exp: Long,
    @Json(name = "character_exp_rate") val expRate: String,
    @Json(name = "character_gender") val gender: String,
    @Json(name = "character_guild_name") val guildName: String?,
    @Json(name = "character_image") val image: ImageUrl,
    @Json(name = "character_level") val level: Int,
    @Json(name = "character_name") val name: String,
    @Json(name = "date") val date: String,
    @Json(name = "world_name") val worldName: String
)