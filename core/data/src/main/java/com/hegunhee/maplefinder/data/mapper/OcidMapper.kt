package com.hegunhee.maplefinder.data.mapper

import com.hegunhee.maplefinder.data.api.model.OcidResponse
import com.hegunhee.maplefinder.model.character.Ocid

fun OcidResponse.toModel() : Ocid {
    return Ocid(this.id)
}