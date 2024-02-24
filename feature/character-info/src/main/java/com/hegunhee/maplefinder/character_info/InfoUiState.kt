package com.hegunhee.maplefinder.character_info

import com.hegunhee.maplefinder.model.character.Character

sealed interface InfoUiState {

    object Loading : InfoUiState

    data class Search(
        val character : Character
    ) : InfoUiState

    object Error : InfoUiState
}