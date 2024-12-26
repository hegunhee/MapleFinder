package com.hegunhee.maplefinder.character_info

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.ui.CharacterSearchBar
import com.hegunhee.maplefinder.ui.MapleTopBar
import com.hegunhee.maplefinder.ui.dialog.MapleDatePickerDialog
import com.hegunhee.maplefinder.ui.surface.CharacterSurface
import com.hegunhee.maplefinder.ui.surface.ErrorSurface
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil.defaultDateString
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil.toTimeMills

@Composable
fun InfoScreenRoot(
    viewModel: InfoViewModel = hiltViewModel(),
    onNavigationIconClick: () -> Unit,
    onItemDetailButtonClick: (ocid: String,date: String) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val (searchQuery, onQueryChanged) = rememberSaveable { mutableStateOf("") }
    val (searchDate, onDateChanged) = rememberSaveable { mutableStateOf(defaultDateString()) }
    InfoScreen(
        uiState = uiState,
        searchQuery = searchQuery,
        searchDate = searchDate,
        onNavigationIconClick = onNavigationIconClick,
        onSearchCharacterClick = viewModel::onSearchCharacterClick,
        onQueryChange = onQueryChanged,
        onItemDetailButtonClick = onItemDetailButtonClick,
        onDateSelected = onDateChanged,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InfoScreen(
    uiState: InfoUiState,
    searchQuery: String,
    searchDate: String,
    onNavigationIconClick: () -> Unit,
    onSearchCharacterClick: (name: String, date: String) -> Unit,
    onQueryChange: (String) -> Unit,
    onItemDetailButtonClick: (ocid: String,date: String) -> Unit,
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
                title = "캐릭터 정보 검색",
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
                onSearchCharacterClick = onSearchCharacterClick,
                onQueryChange = onQueryChange,
                onDatePickerShowClick = { onDatePickerValueChange(true) },
                keyboardController = keyboardController
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            when (uiState) {
                InfoUiState.Loading -> {}
                is InfoUiState.Search -> {
                    CharacterSurface(
                        character = uiState.character,
                        onItemDetailButtonClick = onItemDetailButtonClick
                    )
                }

                is InfoUiState.Error -> {
                    ErrorSurface(exception = uiState.throwable)
                }
            }
        }
    }
}

@Preview
@Composable
private fun InfoScreenPreview() {
    InfoScreen(
        uiState = InfoUiState.Loading,
        searchQuery = "",
        searchDate = defaultDateString(),
        onNavigationIconClick = {  },
        onSearchCharacterClick = {name,date->},
        onQueryChange = {  },
        onItemDetailButtonClick = {ocid, date->  },
        onDateSelected = {},
    )
}
