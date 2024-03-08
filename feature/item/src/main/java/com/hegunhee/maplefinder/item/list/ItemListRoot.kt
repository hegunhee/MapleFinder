package com.hegunhee.maplefinder.item.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
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
import com.hegunhee.maplefinder.ui.surface.item.DetailItemSurface

@Composable
fun ItemListScreenRoot(
    viewModel : ItemListViewModel = hiltViewModel(),
    ocid : String,
    slot : String,
    popBackStack : () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    LaunchedEffect(key1 = ocid) {
        viewModel.fetchData(ocid)
    }

    Button(onClick = popBackStack) {
        Text(text = "$ocid $slot")
    }
    ItemListScreen(
        uiState = uiState,
        slot = slot,
        popBackStack = popBackStack,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun ItemListScreen(
    uiState: ItemListUiState,
    popBackStack: () -> Unit,
    slot : String
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "아이템 정보") },
                navigationIcon = { IconButton(onClick = popBackStack) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close ItemList"
                    )
                } }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            when(uiState) {
                ItemListUiState.Loading -> { }
                is ItemListUiState.Success -> {
                    val pagerState = rememberPagerState(
                        pageCount = { uiState.itemList.size},
                        initialPage = uiState.itemList.indexOf(uiState.itemList.find { it.slot == slot })
                    )
                    HorizontalPager(state = pagerState) { page ->
                        DetailItemSurface(scrollState = scrollState,item = uiState.itemList[page])
                    }
                }
                ItemListUiState.Error -> { }
            }
        }
    }
}
