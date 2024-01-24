package com.hegunhee.maplefinder.dojang_record

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.domain.usecase.GetCharacterDojangUseCase
import com.hegunhee.maplefinder.model.character.CharacterDojang
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

    private val _characterDojang : MutableStateFlow<CharacterDojang> = MutableStateFlow(CharacterDojang("","","","",0,"",0))
    val characterDojang : StateFlow<CharacterDojang> = _characterDojang.asStateFlow()

    init {
        getDojangTest()
    }
    fun getDojangTest() {
        viewModelScope.launch {
            getCharacterDojangUseCase("엔버는함초롱","2024-01-23")
                .onSuccess {
                    _characterDojang.value = it
                }.onFailure {

                }
        }
    }
}