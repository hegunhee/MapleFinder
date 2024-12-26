package com.hegunhee.maplefinder.character_info.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.maplefinder.character_info.InfoScreenRoot

const val CHARACTER_INFO_ROUTE = "Character-Info"

fun NavGraphBuilder.infoNavGraph(
    onNavigationIconClick: () -> Unit,
    onItemDetailButtonClick: (ocid: String,date: String) -> Unit
) {
    composable(route = CHARACTER_INFO_ROUTE) {
        InfoScreenRoot(
            onNavigationIconClick = onNavigationIconClick,
            onItemDetailButtonClick = onItemDetailButtonClick
        )
    }
}
