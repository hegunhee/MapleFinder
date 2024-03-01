package com.hegunhee.maplefinder.item.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ItemDetailScreenRoot(
    ocid : String
) {
    ItemDetailScreen(ocid = ocid)
}

@Composable
private fun ItemDetailScreen(ocid : String) {
    Text(text = "ocid = $ocid")
}