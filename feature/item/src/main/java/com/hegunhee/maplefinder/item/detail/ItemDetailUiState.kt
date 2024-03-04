package com.hegunhee.maplefinder.item.detail

import com.hegunhee.maplefinder.model.character.item.CharacterEquipmentItem

sealed interface ItemDetailUiState {

    object Loading : ItemDetailUiState

    data class Success(val equipmentItem : CharacterEquipmentItem) : ItemDetailUiState

    object Error : ItemDetailUiState
}