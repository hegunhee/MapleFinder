package com.hegunhee.maplefinder.data.api

import com.hegunhee.maplefinder.data.api.model.OcidResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MapleOcidApi {

    @GET("id")
    suspend fun getOcid(
        @Query("character_name") characterName : String
    ) : OcidResponse
}