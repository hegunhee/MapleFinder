package com.hegunhee.maplefinder.item.search

sealed interface ItemSearchUiState {

    object Loading : ItemSearchUiState

    data class Error(
        val throwable: Throwable
    ) : ItemSearchUiState

}
