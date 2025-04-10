package com.hegunhee.maplefinder.item.detail

import com.hegunhee.maplefinder.model.character.item.normal.CharacterEquipmentItem

sealed interface ItemDetailUiState {

    object Loading : ItemDetailUiState

    data class Success(val equipmentItem: CharacterEquipmentItem) : ItemDetailUiState

    data class Error(
        val throwable: Throwable
    ) : ItemDetailUiState

}
