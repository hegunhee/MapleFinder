package com.hegunhee.maplefinder.item

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hegunhee.maplefinder.item.detail.ItemDetailScreenRoot

fun NavController.navigateItemDetail(ocid : String) {
    navigate(ItemNavGraph.detailRoute(ocid))
}

fun NavGraphBuilder.itemNavGraph(
    onNavigationIconClick : () -> Unit
) {
    composable(route = ItemNavGraph.searchRoute) {
        ItemSearchScreenRoot(onNavigationIconClick)
    }

    composable(route = ItemNavGraph.detailRoute("{ocid}"),
        arguments  = listOf(
            navArgument("ocid") {
                type = NavType.StringType
            }
        )){ navBackStackEntry ->
        val ocid = navBackStackEntry.arguments?.getString("ocid") ?: ""
        ItemDetailScreenRoot(
            ocid = ocid,
            onNavigationIconClick = onNavigationIconClick
        )
    }
}

object ItemNavGraph {
    const val searchRoute = "Search"

    fun detailRoute(ocid : String) : String {
        return "$searchRoute/$ocid"
    }
}