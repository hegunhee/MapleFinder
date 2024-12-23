package com.hegunhee.maplefinder.data.datasource.remote

import com.hegunhee.maplefinder.data.api.model.character.CharacterDojangResponse
import com.hegunhee.maplefinder.data.api.model.OcidResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterAbilityResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterBasicResponse
import com.hegunhee.maplefinder.data.api.model.character.item.ItemResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterHyperStatResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterStatResponse

interface RemoteDataSource {

    suspend fun getCharacterOcid(
        characterName: String
    ): OcidResponse

    suspend fun getCharacterDojang(
        ocid: String,
        date: String
    ): CharacterDojangResponse

    suspend fun getCharacterBasic(
        ocid: String,
        date: String
    ): CharacterBasicResponse

    suspend fun getCharacterStat(
        ocid: String,
        date: String
    ): CharacterStatResponse

    suspend fun getCharacterHyperStat(
        ocid: String,
        date: String
    ): CharacterHyperStatResponse

    suspend fun getCharacterAbility(
        ocid: String,
        date: String
    ): CharacterAbilityResponse

    suspend fun getCharacterItem(
        ocid: String,
        date: String
    ): ItemResponse

}
