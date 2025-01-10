package com.hegunhee.maplefinder.data.api.model.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ErrorDetail(
    @SerialName("name") val name: String,
    @SerialName("message") val message: String,
)
