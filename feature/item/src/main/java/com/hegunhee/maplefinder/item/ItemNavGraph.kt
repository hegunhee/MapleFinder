package com.hegunhee.maplefinder.item

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hegunhee.maplefinder.item.detail.ItemDetailScreenRoot
import com.hegunhee.maplefinder.item.list.ItemListScreenRoot
import com.hegunhee.maplefinder.item.search.ItemSearchScreenRoot

fun NavController.navigateItemDetail(ocid : String) {
    navigate(ItemNavGraph.detailRoute(ocid))
}

fun NavController.navigateItemList(ocid : String,slotName: String) {
    navigate(ItemNavGraph.detailListRoute(ocid,slotName))
}
fun NavGraphBuilder.itemNavGraph(
    onNavigationIconClick : () -> Unit,
    onPopBackStack : () -> Unit,
    onItemListButtonClick : (String,String) -> Unit,
    onItemDetailButtonClick : (String) -> Unit
) {
    composable(route = ItemNavGraph.searchRoute) {
        ItemSearchScreenRoot(
            onNavigationIconClick = onNavigationIconClick,
            onSearchCharacterItemClick = onItemDetailButtonClick
        )
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
            onNavigationIconClick = onNavigationIconClick,
            onItemListButtonClick = onItemListButtonClick
        )
    }
    composable(route = ItemNavGraph.detailListRoute("{ocid}","{slotName}"),
        arguments = listOf(
            navArgument("ocid") {
                type = NavType.StringType
            }
        )) { navBackStackEntry ->
        val ocid = navBackStackEntry.arguments?.getString("ocid") ?: ""
        val slotName = navBackStackEntry.arguments?.getString("slotName") ?: ""
        ItemListScreenRoot(
            ocid = ocid,
            slot = slotName,
            popBackStack = onPopBackStack
        )
    }
}

object ItemNavGraph {
    const val searchRoute = "Search"

    private const val listRoute = "List"

    fun detailRoute(ocid : String) : String {
        return "$searchRoute/$ocid"
    }

    fun detailListRoute(ocid : String,slotName : String) : String {
        return "$listRoute/$ocid/$slotName"
    }
}