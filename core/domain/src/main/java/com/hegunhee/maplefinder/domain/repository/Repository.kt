package com.hegunhee.maplefinder.domain.repository

import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.CharacterDojang
import com.hegunhee.maplefinder.model.character.Ocid
import com.hegunhee.maplefinder.model.character.item.CharacterEquipmentItem
import com.hegunhee.maplefinder.model.character.item.Item

interface Repository {

    suspend fun getCharacterOcid(
        characterName : String
    ) : Result<Ocid>

    suspend fun getCharacterDojangInfo(
        characterName : String,
        date : String
    ) : Result<CharacterDojang>

    suspend fun getCharacter(
        characterName: String,
        date : String
    ) : Result<Character>

    suspend fun getCharacterItem(
        ocid : String,
        date : String
    ) : Result<CharacterEquipmentItem>

    suspend fun getCharacterItemList(
        ocid : String,
        date : String
    ) : Result<List<Item>>

}