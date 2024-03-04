package com.hegunhee.maplefinder.domain.usecase

import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.item.CharacterEquipmentItem
import javax.inject.Inject

class GetCharacterEquipmentItemUseCase @Inject constructor(
    private val repository: Repository
){

    suspend operator fun invoke(ocid : String,date : String) : Result<CharacterEquipmentItem> {
        return repository.getCharacterItem(ocid,date)
    }
}