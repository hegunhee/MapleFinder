package com.hegunhee.maplefinder.item.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.domain.usecase.GetCharacterItemListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val getCharacterItemListUseCase: GetCharacterItemListUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ItemListUiState> = MutableStateFlow(ItemListUiState.Loading)
    val uiState: StateFlow<ItemListUiState> = _uiState.asStateFlow()

    fun fetchData(ocid: String, date: String) {
        viewModelScope.launch {
            getCharacterItemListUseCase(
                ocid = ocid,
                date = date,
            ).onSuccess { itemList ->
                _uiState.value = ItemListUiState.Success(itemList)
            }.onFailure {
                _uiState.value = ItemListUiState.Error
            }
        }
    }
}
