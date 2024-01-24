package com.hegunhee.maplefinder.domain.usecase

import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.CharacterDojang
import javax.inject.Inject

class GetCharacterDojangUseCase @Inject constructor(
    private val repository: Repository
){

    suspend operator fun invoke(characterName: String,date : String) : Result<CharacterDojang> {
        return repository.getCharacterDojangInfo(characterName,date)
    }
}