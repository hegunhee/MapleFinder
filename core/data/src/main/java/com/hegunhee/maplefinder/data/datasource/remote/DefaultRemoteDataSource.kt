package com.hegunhee.maplefinder.data.datasource.remote

import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.api.model.CharacterDojangResponse
import com.hegunhee.maplefinder.data.api.model.OcidResponse
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val mapleOcidApi: MapleOcidApi,
    private val mapleCharacterApi: MapleCharacterApi
) : RemoteDataSource {

    override suspend fun getCharacterOcid(characterName: String): OcidResponse {
        return mapleOcidApi.getOcid(characterName)
    }

    override suspend fun getCharacterDojang(ocid: String, date: String): CharacterDojangResponse {
        return mapleCharacterApi.getCharacterDojang(ocid,date)
    }
}