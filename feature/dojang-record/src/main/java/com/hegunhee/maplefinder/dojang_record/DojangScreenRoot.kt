package com.hegunhee.maplefinder.dojang_record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.ui.CharacterSearchBar
import com.hegunhee.maplefinder.ui.MapleTopBar
import com.hegunhee.maplefinder.ui.dialog.MapleDatePickerDialog
import com.hegunhee.maplefinder.ui.surface.DojangSurface
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil.toTimeMills

@Composable
fun DojangScreenRoot(
    viewModel : DojangViewModel = hiltViewModel(),
    onNavigationIconClick : () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val characterQuery = viewModel.searchQuery.collectAsStateWithLifecycle().value
    val searchDate = viewModel.searchDate.collectAsStateWithLifecycle().value

    DojangScreen(
        uiState = uiState,
        characterQuery = characterQuery,
        searchDate = searchDate,
        onNavigationIconClick = onNavigationIconClick,
        onSearchCharacterDojang = viewModel::getCharacterDojang,
        onQueryChange = viewModel::onQueryChange,
        onDateSelected = viewModel::onDateSelectClick,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun DojangScreen(
    uiState : DojangUiState,
    characterQuery : String,
    searchDate : String,
    onNavigationIconClick : () -> Unit,
    onSearchCharacterDojang : (String) -> Unit,
    onQueryChange : (String) -> Unit,
    onDateSelected : (String) -> Unit,
) {
    val (showDateDialog, onDatePickerValueChange) = remember { mutableStateOf(false) }
    if(showDateDialog) {
        MapleDatePickerDialog(
            initialSelectedDateMills = searchDate.toTimeMills(),
            onDateSelected = onDateSelected,
            isSelectableDate = SelectedDateFormatUtil::isSelectableDate,
            onDismiss = { onDatePickerValueChange(false)})
    }
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
                date = searchDate,
                onSearchCharacterClick = onSearchCharacterDojang,
                onQueryChange = onQueryChange,
                onDatePickerShowClick = { onDatePickerValueChange(true)},
                keyboardController = keyboardController
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            when(uiState) {
                DojangUiState.Loading -> { }
                is DojangUiState.Search -> {
                    DojangSurface(characterDojang = uiState.characterDojang)
                }
                DojangUiState.Error -> {
                    Text("존재하지 않는 캐릭터입니다.")
                }
            }
        }
    }
}