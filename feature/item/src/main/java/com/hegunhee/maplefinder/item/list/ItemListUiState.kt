package com.hegunhee.maplefinder.item.list

import com.hegunhee.maplefinder.model.character.item.Item

sealed interface ItemListUiState {

    object Loading : ItemListUiState

    data class Success(
        val itemList: List<Item>,
    ) : ItemListUiState

    object Error : ItemListUiState
}
