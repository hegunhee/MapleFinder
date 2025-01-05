package com.hegunhee.maplefinder.domain.usecase

import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.item.normal.Item
import javax.inject.Inject

class GetCharacterItemListUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(ocid : String,date : String) : Result<List<Item>> {
        return repository.getCharacterItemList(ocid,date)
    }
}