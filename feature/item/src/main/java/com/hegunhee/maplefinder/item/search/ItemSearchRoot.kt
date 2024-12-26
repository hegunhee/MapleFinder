package com.hegunhee.maplefinder.item.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.ui.CharacterSearchBar
import com.hegunhee.maplefinder.ui.MapleTopBar
import com.hegunhee.maplefinder.ui.dialog.MapleDatePickerDialog
import com.hegunhee.maplefinder.ui.surface.ErrorSurface
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil.defaultDateString
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil.toTimeMills

@Composable
fun ItemSearchScreenRoot(
    viewModel: ItemSearchViewModel = hiltViewModel(),
    onNavigationIconClick: () -> Unit,
    onSearchCharacterItemClick: (ocid: String, date: String) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val (searchQuery, onQueryChanged) = remember { mutableStateOf("") }
    val (searchDate, onDateSelected) = rememberSaveable { mutableStateOf(defaultDateString()) }

    LaunchedEffect(true) {
        viewModel.navActions.collect {
            when (it) {
                is ItemNavActions.Detail -> {
                    onSearchCharacterItemClick(it.ocid, it.date)
                }
            }
        }
    }

    ItemSearchScreen(
        uiState = uiState,
        searchQuery = searchQuery,
        searchDate = searchDate,
        onNavigationIconClick = onNavigationIconClick,
        onSearchCharacterItemClick = viewModel::characterOcidSearch,
        onQueryChange = onQueryChanged,
        onDateSelected = onDateSelected
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ItemSearchScreen(
    uiState: ItemSearchUiState,
    searchQuery: String,
    searchDate: String,
    onNavigationIconClick: () -> Unit,
    onSearchCharacterItemClick: (name: String, date: String) -> Unit,
    onQueryChange: (String) -> Unit,
    onDateSelected: (String) -> Unit,
) {
    val (showDateDialog, onDatePickerValueChange) = remember { mutableStateOf(false) }
    if (showDateDialog) {
        MapleDatePickerDialog(
            initialSelectedDateMills = searchDate.toTimeMills(),
            onDateSelected = onDateSelected,
            isSelectableDate = SelectedDateFormatUtil::isSelectableDate,
            onDismiss = { onDatePickerValueChange(false) })
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            MapleTopBar(
                title = "캐릭터 아이템 조회",
                onNavigationIconClick = onNavigationIconClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            CharacterSearchBar(
                searchQuery = searchQuery,
                date = searchDate,
                onSearchCharacterClick = onSearchCharacterItemClick,
                onQueryChange = onQueryChange,
                onDatePickerShowClick = { onDatePickerValueChange(true) },
                keyboardController = keyboardController,
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            if (uiState is ItemSearchUiState.Error) {
                ErrorSurface(exception = uiState.throwable)
            }
        }

    }
}
