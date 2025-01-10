package com.hegunhee.maplefinder.data.api.model.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ErrorResponse(
    @SerialName("error") val errorDetail: ErrorDetail,
)
