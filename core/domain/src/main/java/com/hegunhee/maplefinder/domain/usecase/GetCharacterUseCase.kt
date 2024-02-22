package com.hegunhee.maplefinder.domain.usecase

import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.Character
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(characterName : String, date : String) : Result<Character> {
        return repository.getCharacter(characterName,date)
    }
}