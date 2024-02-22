package com.hegunhee.maplefinder.domain.repository

import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.CharacterDojang

interface Repository {

    suspend fun getCharacterDojangInfo(
        characterName : String,
        date : String
    ) : Result<CharacterDojang>

    suspend fun getCharacter(
        characterName: String,
        date : String
    ) : Result<Character>

}