package com.hegunhee.maplefinder.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ItemSearchScreenRoot(
    onNavigationIconClick : () -> Unit
) {
    ItemSearchScreen()
}

@Composable
private fun ItemSearchScreen() {
    Text(text = "아이템 검색")
}