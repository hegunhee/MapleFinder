package com.hegunhee.maplefinder.character_info

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.infoNavGraph(
    onNavigationIconClick : () -> Unit
) {
    composable(route = InfoNavGraph.infoRoute) {
        InfoScreenRoot(
            onNavigationIconClick = onNavigationIconClick
        )
    }
}

object InfoNavGraph {
    const val infoRoute = "Info"
}