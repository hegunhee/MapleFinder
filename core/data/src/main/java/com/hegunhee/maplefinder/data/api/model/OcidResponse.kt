package com.hegunhee.maplefinder.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OcidResponse(
    @Json(name = "ocid") val id : String
)