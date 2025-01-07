package com.hegunhee.maplefinder.item.cash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CashItemRoot(
    viewModel: CashItemViewModel = hiltViewModel(),
    ocid: String,
    date: String,
    popBackStack: () -> Unit,
) {
    LaunchedEffect(key1 = viewModel.uiState) {
        viewModel.fetchCashItem(ocid, date)
    }
    Text("ocid = $ocid date = $date")
}
