package com.hegunhee.maplefinder.domain.repository

import com.hegunhee.maplefinder.model.character.CharacterDojang

interface Repository {

    suspend fun getCharacterDojangInfo(
        characterName : String,
        date : String
    ) : Result<CharacterDojang>

}