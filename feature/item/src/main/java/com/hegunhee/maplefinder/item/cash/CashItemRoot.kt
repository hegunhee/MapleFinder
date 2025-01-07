package com.hegunhee.maplefinder.item.cash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.item.cash.CashItemUiState.Error
import com.hegunhee.maplefinder.item.cash.CashItemUiState.Loading
import com.hegunhee.maplefinder.item.cash.CashItemUiState.Success
import com.hegunhee.maplefinder.item.navigation.cashItemRoute
import com.hegunhee.maplefinder.ui.surface.ErrorSurface
import com.hegunhee.maplefinder.ui.surface.item.CashItemSurface

@Composable
fun CashItemRoot(
    viewModel: CashItemViewModel = hiltViewModel(),
    characterName: String,
    date: String,
    popBackStack: () -> Unit,
) {
    LaunchedEffect(key1 = viewModel.uiState) {
        viewModel.fetchCashItem(characterName, date)
    }
    CashItemScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        date = date,
        popBackStack = popBackStack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CashItemScreen(
    uiState: CashItemUiState,
    date: String,
    popBackStack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "캐시 아이템 정보") },
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close CashItem"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            when (uiState) {
                Loading -> {}
                is Success -> {
                    CashItemSurface(
                        cashItemCharacter = uiState.cashItemCharacter,
                        date = date,
                    )
                }

                is Error -> {
                    ErrorSurface(uiState.exception)
                }
            }
        }
    }
}
