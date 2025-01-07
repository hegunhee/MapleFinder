package com.hegunhee.maplefinder.item.cash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CashItemRoot(
    ocid: String,
    date: String,
    popBackStack: () -> Unit,
) {
    Text("ocid = $ocid date = $date")
}
