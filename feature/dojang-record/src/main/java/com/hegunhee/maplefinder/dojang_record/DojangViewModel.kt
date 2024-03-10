package com.hegunhee.maplefinder.dojang_record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.domain.usecase.GetCharacterDojangUseCase
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DojangViewModel @Inject constructor(
    private val getCharacterDojangUseCase: GetCharacterDojangUseCase
) : ViewModel() {

    private val _uiState : MutableStateFlow<DojangUiState> = MutableStateFlow(DojangUiState.Loading)
    val uiState : StateFlow<DojangUiState> = _uiState.asStateFlow()

    private val _searchQuery : MutableStateFlow<String> = MutableStateFlow("")
    val searchQuery : StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchDate : MutableStateFlow<String> = MutableStateFlow(SelectedDateFormatUtil.defaultDateString())
    val searchDate : StateFlow<String> = _searchDate.asStateFlow()

    fun onQueryChange(query : String) {
        _searchQuery.value = query
    }

    fun getCharacterDojang(characterName : String) {
        viewModelScope.launch {
            getCharacterDojangUseCase(characterName = characterName,date = searchDate.value)
                .onSuccess { characterDojang ->
                    _uiState.value = DojangUiState.Search(characterDojang = characterDojang)
                }.onFailure {
                    _uiState.value = DojangUiState.Error
                }
        }
    }

    fun onDateSelectClick(date : String) {
        _searchDate.value = date
    }
}