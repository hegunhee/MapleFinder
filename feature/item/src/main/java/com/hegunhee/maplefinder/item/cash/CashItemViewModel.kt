package com.hegunhee.maplefinder.item.cash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.domain.usecase.GetCharacterCashItemUseCase
import com.hegunhee.maplefinder.item.cash.CashItemUiState.Error
import com.hegunhee.maplefinder.item.cash.CashItemUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CashItemViewModel @Inject constructor(
    private val getCharacterCashItemUseCase: GetCharacterCashItemUseCase,
): ViewModel() {

    private val _uiState: MutableStateFlow<CashItemUiState> = MutableStateFlow(CashItemUiState.Loading)
    val uiState: StateFlow<CashItemUiState> = _uiState.asStateFlow()

    fun fetchCashItem(
        characterName: String,
        date: String,
    ) {
        viewModelScope.launch {
            getCharacterCashItemUseCase(characterName,date)
                .onSuccess {
                    _uiState.value = Success(it)
                }.onFailure {
                    _uiState.value = Error(it)
                }
        }
    }

}
