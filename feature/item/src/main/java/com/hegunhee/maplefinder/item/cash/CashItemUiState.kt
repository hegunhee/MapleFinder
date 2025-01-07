package com.hegunhee.maplefinder.item.cash

import com.hegunhee.maplefinder.model.character.item.cash.CashItemCharacter

sealed interface CashItemUiState {

    object Loading : CashItemUiState

    data class Success(
        val cashItemCharacter: CashItemCharacter,
    ) : CashItemUiState

    object Error : CashItemUiState

}
