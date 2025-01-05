package com.hegunhee.maplefinder.data.datasource.remote

import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.api.model.character.CharacterDojangResponse
import com.hegunhee.maplefinder.data.api.model.OcidResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterAbilityResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterBasicResponse
import com.hegunhee.maplefinder.data.api.model.character.item.normal.ItemResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterHyperStatResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterStatResponse
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val mapleOcidApi: MapleOcidApi,
    private val mapleCharacterApi: MapleCharacterApi
) : RemoteDataSource {

    override suspend fun getCharacterOcid(characterName: String): OcidResponse {
        return mapleOcidApi.getOcid(characterName)
    }

    override suspend fun getCharacterDojang(ocid: String, date: String): CharacterDojangResponse {
        return mapleCharacterApi.getCharacterDojang(ocid, date)
    }

    override suspend fun getCharacterBasic(ocid: String, date: String): CharacterBasicResponse {
        return mapleCharacterApi.getCharacterBasic(ocid, date)
    }

    override suspend fun getCharacterStat(ocid: String, date: String): CharacterStatResponse {
        return mapleCharacterApi.getCharacterStat(ocid, date)
    }

    override suspend fun getCharacterHyperStat(
        ocid: String,
        date: String
    ): CharacterHyperStatResponse {
        return mapleCharacterApi.getCharacterHyperStat(ocid, date)
    }

    override suspend fun getCharacterAbility(ocid: String, date: String): CharacterAbilityResponse {
        return mapleCharacterApi.getCharacterAbility(ocid, date)
    }

    override suspend fun getCharacterItem(ocid: String, date: String): ItemResponse {
        return mapleCharacterApi.getCharacterItem(ocid, date)
    }

}
