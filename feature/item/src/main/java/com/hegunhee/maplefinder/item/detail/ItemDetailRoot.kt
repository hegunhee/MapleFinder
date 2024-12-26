package com.hegunhee.maplefinder.item.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.ui.MapleTopBar
import com.hegunhee.maplefinder.ui.surface.CharacterEquipmentItemSurface
import com.hegunhee.maplefinder.ui.surface.ErrorSurface

@Composable
fun ItemDetailScreenRoot(
    viewModel : ItemDetailViewModel = hiltViewModel(),
    ocid : String,
    date : String,
    onNavigationIconClick : () -> Unit,
    onItemListButtonClick : (String,String) -> Unit
) {
    date
    LaunchedEffect(key1 = viewModel.uiState) {
        viewModel.fetchItemData(ocid,date)
    }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    ItemDetailScreen(
        uiState = uiState,
        onNavigationIconClick = onNavigationIconClick,
        onItemListButtonClick = onItemListButtonClick
    )
}

@Composable
private fun ItemDetailScreen(
    uiState : ItemDetailUiState,
    onNavigationIconClick: () -> Unit,
    onItemListButtonClick : (String,String) -> Unit
) {
    Scaffold(
        topBar = {
            MapleTopBar(
                title = "캐릭터 장비 정보",
                onNavigationIconClick = onNavigationIconClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            when(uiState) {
                ItemDetailUiState.Loading -> { }
                is ItemDetailUiState.Success -> {
                    CharacterEquipmentItemSurface(uiState.equipmentItem, onItemListButtonClick)
                }
                is ItemDetailUiState.Error -> {
                    ErrorSurface(exception = uiState.throwable)
                }
            }
        }
    }
}
