package com.hegunhee.maplefinder.data.api.model.character

import com.hegunhee.maplefinder.model.ImageUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterBasicResponse(
    @SerialName("character_class") val jobName: String,
    @SerialName("character_class_level") val jobLevel: String,
    @SerialName("character_exp") val exp: Long,
    @SerialName("character_exp_rate") val expRate: String,
    @SerialName("character_gender") val gender: String,
    @SerialName("character_guild_name") val guildName: String?,
    @SerialName("character_image") val image: ImageUrl,
    @SerialName("character_level") val level: Int,
    @SerialName("character_name") val name: String,
    @SerialName("date") val date: String,
    @SerialName("world_name") val worldName: String
)