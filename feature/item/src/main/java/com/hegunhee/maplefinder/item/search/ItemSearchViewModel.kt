package com.hegunhee.maplefinder.item.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.domain.usecase.GetCharacterOcidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemSearchViewModel @Inject constructor(
    private val getCharacterOcidUseCase: GetCharacterOcidUseCase
) : ViewModel() {

    private val _navActions : MutableSharedFlow<ItemNavActions> = MutableSharedFlow()
    val navActions : SharedFlow<ItemNavActions> = _navActions.asSharedFlow()

    private val _uiState : MutableStateFlow<ItemSearchUiState> = MutableStateFlow(ItemSearchUiState.Loading)
    val uiState : StateFlow<ItemSearchUiState> = _uiState.asStateFlow()

    private val _searchQuery : MutableStateFlow<String> = MutableStateFlow("")
    val searchQuery : StateFlow<String> = _searchQuery.asStateFlow()

    fun characterOcidSearch(characterName : String) {
        viewModelScope.launch {
            getCharacterOcidUseCase(characterName)
                .onSuccess { ocid ->
                    _navActions.emit(ItemNavActions.Detail(ocid.id))
                    _uiState.value = ItemSearchUiState.Loading
                }.onFailure {
                    _uiState.value = ItemSearchUiState.Error(it)
                }
        }
    }

    fun onQueryChange(query : String) {
        _searchQuery.value = query
    }
}