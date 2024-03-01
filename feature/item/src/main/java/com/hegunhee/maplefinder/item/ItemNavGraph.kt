package com.hegunhee.maplefinder.item

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hegunhee.maplefinder.item.detail.ItemDetailScreenRoot

fun NavGraphBuilder.itemNavGraph(
    onNavigationIconClick : () -> Unit
) {
    composable(route = ItemNavGraph.searchRoute) {
        ItemSearchScreenRoot()
    }

    composable(route = ItemNavGraph.detailRoute("{ocid}"),
        arguments  = listOf(
            navArgument("ocid") {
                type = NavType.StringType
            }
        )){ navBackStackEntry ->
        val ocid = navBackStackEntry.arguments?.getString("ocid") ?: ""
        ItemDetailScreenRoot(ocid = ocid)
    }
}

object ItemNavGraph {
    const val searchRoute = "Search"

    fun detailRoute(ocid : String) : String {
        return "$searchRoute/$ocid"
    }
}