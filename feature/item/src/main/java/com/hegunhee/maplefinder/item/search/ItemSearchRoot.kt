package com.hegunhee.maplefinder.item.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.ui.CharacterNameSearchBar
import com.hegunhee.maplefinder.ui.MapleTopBar
import com.hegunhee.maplefinder.ui.surface.ErrorSurface
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil

@Composable
fun ItemSearchScreenRoot(
    viewModel: ItemSearchViewModel = hiltViewModel(),
    onNavigationIconClick : () -> Unit,
    onSearchCharacterItemClick : (String) -> Unit,
) {
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle().value
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    
    LaunchedEffect(true) {
        viewModel.navActions.collect {
            when(it) {
                is ItemNavActions.Detail -> {
                    onSearchCharacterItemClick(it.ocid)
                }
            }
        }
    }

    ItemSearchScreen(
        uiState = uiState,
        searchQuery = searchQuery,
        onNavigationIconClick = onNavigationIconClick,
        onSearchCharacterItemClick = viewModel::characterOcidSearch,
        onQueryChange = viewModel::onQueryChange
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ItemSearchScreen(
    uiState: ItemSearchUiState,
    searchQuery : String,
    onNavigationIconClick: () -> Unit,
    onSearchCharacterItemClick : (name: String,date: String) -> Unit,
    onQueryChange : (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = { MapleTopBar(
            title = "캐릭터 아이템 조회",
            onNavigationIconClick = onNavigationIconClick
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            CharacterNameSearchBar(
                searchQuery = searchQuery,
                searchDate = SelectedDateFormatUtil.defaultDateString(),
                onSearchCharacterClick = onSearchCharacterItemClick,
                onQueryChange = onQueryChange,
                keyboardController = keyboardController
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            if(uiState is ItemSearchUiState.Error) {
                ErrorSurface(exception = uiState.throwable)
            }
        }

    }
}