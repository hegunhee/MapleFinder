package com.hegunhee.maplefinder.dojang_record.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.maplefinder.dojang_record.DojangScreenRoot

const val DOJANG_ROUTE = "Dojang"

fun NavGraphBuilder.dojangNavGraph(
    onNavigationIconClick: () -> Unit
) {
    composable(route = DOJANG_ROUTE) {
        DojangScreenRoot(
            onNavigationIconClick = onNavigationIconClick
        )
    }
}
