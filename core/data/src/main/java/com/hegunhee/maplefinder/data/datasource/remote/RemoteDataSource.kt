package com.hegunhee.maplefinder.data.datasource.remote

import com.hegunhee.maplefinder.data.api.model.character.CharacterDojangResponse
import com.hegunhee.maplefinder.data.api.model.OcidResponse

interface RemoteDataSource {

    suspend fun getCharacterOcid(
        characterName : String
    ) : OcidResponse

    suspend fun getCharacterDojang(
        ocid : String,
        date : String
    ) : CharacterDojangResponse
}