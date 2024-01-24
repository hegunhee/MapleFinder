package com.hegunhee.maplefinder.dojang_record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.model.character.CharacterDojang
import com.hegunhee.maplefinder.ui.MapleTopBar

@Composable
fun DojangScreenRoot(
    viewModel : DojangViewModel = hiltViewModel(),
    onNavigationIconClick : () -> Unit,
) {
    val uiState = viewModel.characterDojang.collectAsStateWithLifecycle().value
    DojangScreen(
        onNavigationIconClick = onNavigationIconClick,
        uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DojangScreen(
    onNavigationIconClick : () -> Unit,
    characterDojang : CharacterDojang
) {
    Scaffold(
        topBar = { MapleTopBar(
            title = "무릉도장 검색",
            onNavigationIconClick = onNavigationIconClick
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text("무릉도장 화면입니다.")
            Text(characterDojang.toString())
        }
    }
}