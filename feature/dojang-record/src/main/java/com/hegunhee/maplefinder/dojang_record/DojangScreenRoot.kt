package com.hegunhee.maplefinder.dojang_record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.model.character.CharacterDojang
import com.hegunhee.maplefinder.ui.CharacterSearchBar
import com.hegunhee.maplefinder.ui.MapleTopBar
import com.hegunhee.maplefinder.ui.surface.DojangSurface

@Composable
fun DojangScreenRoot(
    viewModel : DojangViewModel = hiltViewModel(),
    onNavigationIconClick : () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val characterQuery = viewModel.searchQuery.collectAsStateWithLifecycle().value
    when(uiState) {
        DojangUiState.Loading -> { }
        is DojangUiState.Search -> {
            DojangScreen(
                characterQuery = characterQuery,
                characterDojang = uiState.characterDojang,
                onNavigationIconClick = onNavigationIconClick,
                onSearchCharacterDojang = viewModel::getCharacterDojang,
                onQueryChange = viewModel::onQueryChange
            )
        }
        DojangUiState.Error -> { }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun DojangScreen(
    characterQuery : String,
    characterDojang : CharacterDojang?,
    onNavigationIconClick : () -> Unit,
    onSearchCharacterDojang : (String) -> Unit,
    onQueryChange : (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = { MapleTopBar(
            title = "무릉도장 검색",
            onNavigationIconClick = onNavigationIconClick
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            CharacterSearchBar(
                searchQuery = characterQuery,
                onSearchCharacterClick = onSearchCharacterDojang,
                onQueryChange = onQueryChange,
                keyboardController = keyboardController
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            if(characterDojang != null) {
                DojangSurface(characterDojang = characterDojang)
            }
        }
    }
}