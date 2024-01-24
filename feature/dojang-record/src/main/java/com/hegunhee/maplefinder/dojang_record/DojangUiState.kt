package com.hegunhee.maplefinder.dojang_record

import com.hegunhee.maplefinder.model.character.CharacterDojang

sealed interface DojangUiState {

    object Loading : DojangUiState

    data class Search(
        val characterDojang : CharacterDojang?
    ) : DojangUiState

    object Error : DojangUiState
}