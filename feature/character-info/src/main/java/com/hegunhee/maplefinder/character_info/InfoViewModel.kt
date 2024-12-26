package com.hegunhee.maplefinder.character_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel(){

    private val _uiState : MutableStateFlow<InfoUiState> = MutableStateFlow(InfoUiState.Loading)
    val uiState : StateFlow<InfoUiState> = _uiState.asStateFlow()

    fun onSearchCharacterClick(query : String,searchDate: String) {
        viewModelScope.launch {
            getCharacterUseCase(characterName = query,date = searchDate)
                .onSuccess {
                    _uiState.value = InfoUiState.Search(it)
                }.onFailure {
                    _uiState.value = InfoUiState.Error(it)
                }
        }
    }
}
