package com.hegunhee.maplefinder.item.cash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CashItemViewModel @Inject constructor(): ViewModel() {

    private val _uiState: MutableStateFlow<CashItemUiState> = MutableStateFlow(CashItemUiState.Loading)
    val uiState: StateFlow<CashItemUiState> = _uiState.asStateFlow()

    fun fetchCashItem(
        ocid: String,
        date: String,
    ) {

    }

}
