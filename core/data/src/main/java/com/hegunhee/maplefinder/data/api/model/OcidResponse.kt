package com.hegunhee.maplefinder.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OcidResponse(
    @SerialName("ocid") val id : String
)
