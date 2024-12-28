package com.hegunhee.maplefinder.item.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.domain.usecase.GetCharacterEquipmentItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val getCharacterEquipmentItem: GetCharacterEquipmentItemUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ItemDetailUiState> = MutableStateFlow(ItemDetailUiState.Loading)
    val uiState: StateFlow<ItemDetailUiState> = _uiState.asStateFlow()

    fun fetchItemData(ocid: String, date: String) {
        viewModelScope.launch {
            getCharacterEquipmentItem(ocid, date)
                .onSuccess {
                    _uiState.value = ItemDetailUiState.Success(it)
                }.onFailure {
                    _uiState.value = ItemDetailUiState.Error(it)
                }
        }
    }

}
