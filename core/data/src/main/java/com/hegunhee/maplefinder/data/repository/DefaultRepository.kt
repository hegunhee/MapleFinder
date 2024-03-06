package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.datasource.remote.RemoteDataSource
import com.hegunhee.maplefinder.data.mapper.findMainStatName
import com.hegunhee.maplefinder.data.mapper.toCharacterDojang
import com.hegunhee.maplefinder.data.mapper.toModel
import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.CharacterDojang
import com.hegunhee.maplefinder.model.character.item.CharacterEquipmentItem
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getCharacterDojangInfo(characterName: String,date : String): Result<CharacterDojang> = coroutineScope {
        runCatching {
            val ocid = remoteDataSource.getCharacterOcid(characterName).id
            val characterDojangResponse = remoteDataSource.getCharacterDojang(ocid,date).toCharacterDojang(characterName)
            characterDojangResponse
        }
    }

    override suspend fun getCharacter(characterName: String, date: String): Result<Character> = coroutineScope{
        runCatching {
            val ocid = remoteDataSource.getCharacterOcid(characterName).id
            val basicInfo = async { remoteDataSource.getCharacterBasic(ocid, date).toModel() }
            val statInfo = async { remoteDataSource.getCharacterStat(ocid, date).toModel() }
            val hyperStatInfo = async { remoteDataSource.getCharacterHyperStat(ocid, date).toModel() }
            val abilityInfo = remoteDataSource.getCharacterAbility(ocid, date).toModel()
            Character(
                ocid = ocid,
                basic = basicInfo.await(),
                stat = statInfo.await(),
                hyperStat = hyperStatInfo.await(),
                ability = abilityInfo
            )
        }
    }

    override suspend fun getCharacterItem(ocid : String, date: String): Result<CharacterEquipmentItem> = coroutineScope {
        runCatching {
            val basicInfo = async { remoteDataSource.getCharacterBasic(ocid,date).toModel() }
            val itemInfo = async{ remoteDataSource.getCharacterItem(ocid, date) }
            val statInfo = remoteDataSource.getCharacterStat(ocid,date)
            val mainStat = statInfo.detailStatList.findMainStatName()
            CharacterEquipmentItem(
                ocid = ocid,
                mainStat = mainStat,
                basic = basicInfo.await(),
                equipmentItem = itemInfo.await().toModel(),
            )
        }
    }
}