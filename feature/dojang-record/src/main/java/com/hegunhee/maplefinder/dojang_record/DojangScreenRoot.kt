package com.hegunhee.maplefinder.dojang_record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.ui.CharacterNameSearchBar
import com.hegunhee.maplefinder.ui.MapleTopBar
import com.hegunhee.maplefinder.ui.surface.DojangSurface
import com.hegunhee.maplefinder.ui.surface.ErrorSurface
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil.defaultDateString

@Composable
fun DojangScreenRoot(
    viewModel: DojangViewModel = hiltViewModel(),
    onNavigationIconClick: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val (searchQuery, onQueryChanged) = rememberSaveable { mutableStateOf("") }
    val (searchDate) = remember { mutableStateOf(defaultDateString()) }

    DojangScreen(
        uiState = uiState,
        characterQuery = searchQuery,
        searchDate = searchDate,
        onNavigationIconClick = onNavigationIconClick,
        onSearchCharacterDojang = viewModel::getCharacterDojang,
        onQueryChange = onQueryChanged,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun DojangScreen(
    uiState: DojangUiState,
    characterQuery: String,
    searchDate: String,
    onNavigationIconClick: () -> Unit,
    onSearchCharacterDojang: (name: String, date: String) -> Unit,
    onQueryChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            MapleTopBar(
                title = "무릉도장 검색",
                onNavigationIconClick = onNavigationIconClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            CharacterNameSearchBar(
                searchQuery = characterQuery,
                searchDate = searchDate,
                onSearchCharacterClick = onSearchCharacterDojang,
                onQueryChange = onQueryChange,
                keyboardController = keyboardController
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            when (uiState) {
                DojangUiState.Loading -> {}
                is DojangUiState.Search -> {
                    DojangSurface(characterDojang = uiState.characterDojang)
                }

                is DojangUiState.Error -> {
                    ErrorSurface(exception = uiState.throwable)
                }
            }
        }
    }
}
