package com.hegunhee.maplefinder.item.search

sealed interface ItemSearchUiState {

    object Loading : ItemSearchUiState

    data class Success(
        val ocid : String
    ) : ItemSearchUiState

    object Error : ItemSearchUiState
}