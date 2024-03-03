package com.hegunhee.maplefinder.data.api.model.character.stat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterStatResponse(
    @SerialName("character_class")val jobName: String,
    @SerialName("date")val date: String,
    @SerialName("final_stat")val detailStatList: List<DetailStat>,
    @SerialName("remain_ap")val remainAp: Int
)