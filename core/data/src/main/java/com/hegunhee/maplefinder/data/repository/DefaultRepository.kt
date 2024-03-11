package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.api.createResult
import com.hegunhee.maplefinder.data.datasource.remote.RemoteDataSource
import com.hegunhee.maplefinder.data.mapper.findMainStatName
import com.hegunhee.maplefinder.data.mapper.toCharacterDojang
import com.hegunhee.maplefinder.data.mapper.toModel
import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.CharacterDojang
import com.hegunhee.maplefinder.model.character.Ocid
import com.hegunhee.maplefinder.model.character.item.CharacterEquipmentItem
import com.hegunhee.maplefinder.model.character.item.Item
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getCharacterOcid(characterName: String): Result<Ocid> {
        return createResult {
            remoteDataSource.getCharacterOcid(characterName).toModel()
        }
    }

    override suspend fun getCharacterDojangInfo(characterName: String,date : String): Result<CharacterDojang> = coroutineScope {
        createResult {
            val ocid = remoteDataSource.getCharacterOcid(characterName).id
            val characterDojangResponse = remoteDataSource.getCharacterDojang(ocid,date).toCharacterDojang(characterName)
            characterDojangResponse
        }
    }

    override suspend fun getCharacter(characterName: String, date: String): Result<Character> = coroutineScope{
        createResult {
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
        createResult {
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

    override suspend fun getCharacterItemList(ocid: String, date: String): Result<List<Item>> {
        return createResult {
            val itemInfo = remoteDataSource.getCharacterItem(ocid,date)
            itemInfo.itemEquipmentResponse.map { it.toModel() } + itemInfo.dragonEquipment.map { it.toModel() } + itemInfo.mechanicEquipment.map { it.toModel() }
        }
    }
}