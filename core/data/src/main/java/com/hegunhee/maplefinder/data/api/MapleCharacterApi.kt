package com.hegunhee.maplefinder.data.api

import com.hegunhee.maplefinder.data.api.model.CharacterDojangResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MapleCharacterApi {

    @GET("dojang")
    suspend fun getCharacterDojang(
        @Query("ocid") ocid : String,
        @Query("date") date : String
    ) : CharacterDojangResponse
}