package com.hegunhee.maplefinder.item.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ItemDetailScreenRoot(
    viewModel : ItemDetailViewModel = hiltViewModel(),
    ocid : String
) {
    LaunchedEffect(key1 = ocid) {
        viewModel.fetchItemData(ocid)
    }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    if(uiState is ItemDetailUiState.Success) {
        Text(text = uiState.equipmentItem.toString())
    }
}

@Composable
private fun ItemDetailScreen(ocid : String) {
    Text(text = "ocid = $ocid")
}