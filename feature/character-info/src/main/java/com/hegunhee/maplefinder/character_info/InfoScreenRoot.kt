package com.hegunhee.maplefinder.character_info

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun InfoScreenRoot(
    onNavigationIconClick : () -> Unit
) {
    Text(text = "캐릭터 설명 화면")
}