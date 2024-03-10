package com.hegunhee.maplefinder.domain.usecase

import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.Ocid
import javax.inject.Inject

class GetCharacterOcidUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(characterName : String) : Result<Ocid> {
        return repository.getCharacterOcid(characterName)
    }
}