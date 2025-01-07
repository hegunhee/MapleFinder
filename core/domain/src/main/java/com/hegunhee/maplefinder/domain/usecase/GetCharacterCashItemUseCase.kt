package com.hegunhee.maplefinder.domain.usecase

import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.item.cash.CashItemCharacter
import javax.inject.Inject

class GetCharacterCashItemUseCase @Inject constructor(
    private val repository: Repository,
) {

    suspend operator fun invoke(characterName: String, date: String): Result<CashItemCharacter> {
        return repository.getCharacterCashItem(characterName, date)
    }
}