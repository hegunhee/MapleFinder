package com.hegunhee.maplefinder.character_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.ui.CharacterSearchBar
import com.hegunhee.maplefinder.ui.MapleTopBar
import com.hegunhee.maplefinder.ui.surface.CharacterSurface

@Composable
fun InfoScreenRoot(
    viewModel : InfoViewModel = hiltViewModel(),
    onNavigationIconClick : () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()

    InfoScreen(
        uiState = uiState,
        searchQuery = searchQuery.value,
        onNavigationIconClick = onNavigationIconClick,
        onSearchCharacterClick = viewModel::onSearchCharacterClick,
        onQueryChange = viewModel::onSearchQueryChange
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun InfoScreen(
    uiState: InfoUiState,
    searchQuery : String,
    onNavigationIconClick: () -> Unit,
    onSearchCharacterClick : (String) -> Unit,
    onQueryChange : (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = { MapleTopBar(
            title = "캐릭터 정보 검색",
            onNavigationIconClick = onNavigationIconClick
        )}
    ) {  paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ){
            CharacterSearchBar(
                searchQuery = searchQuery,
                onSearchCharacterClick = onSearchCharacterClick,
                onQueryChange = onQueryChange,
                keyboardController = keyboardController
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            when(uiState) {
                InfoUiState.Loading -> { }
                is InfoUiState.Search -> {
                    CharacterSurface(character = uiState.character)
                }
                InfoUiState.Error -> { }
            }
        }
    }
}