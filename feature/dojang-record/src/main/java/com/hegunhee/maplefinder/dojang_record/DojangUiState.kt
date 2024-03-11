package com.hegunhee.maplefinder.dojang_record

import com.hegunhee.maplefinder.model.character.CharacterDojang

sealed interface DojangUiState {

    object Loading : DojangUiState

    data class Search(
        val characterDojang : CharacterDojang
    ) : DojangUiState

    data class Error(
        val throwable : Throwable
    ) : DojangUiState
}