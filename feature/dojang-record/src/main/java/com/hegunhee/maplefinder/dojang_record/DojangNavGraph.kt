package com.hegunhee.maplefinder.dojang_record

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.dojangNavGraph(
    onNavigationIconClick : () -> Unit
) {
    composable(route = DojangNavGraph.dojangRoute) {
        DojangScreenRoot(
            onNavigationIconClick = onNavigationIconClick
        )
    }
}
object DojangNavGraph {
    const val dojangRoute = "Dojang"
}