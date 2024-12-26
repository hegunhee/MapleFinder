package com.hegunhee.maplefinder.item.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hegunhee.maplefinder.item.detail.ItemDetailScreenRoot
import com.hegunhee.maplefinder.item.list.ItemListScreenRoot
import com.hegunhee.maplefinder.item.search.ItemSearchScreenRoot

const val SEARCH_ROUTE = "Search_Item"

fun detailRoute(ocid: String) : String {
    return "${SEARCH_ROUTE}/${ocid}"
}

fun detailListRoute(ocid : String,slotName : String) : String {
    return "${SEARCH_ROUTE}/$ocid/$slotName"
}

fun NavController.navigateItemDetail(ocid : String) {
    navigate(detailRoute(ocid))
}

fun NavController.navigateItemList(ocid : String,slotName: String) {
    navigate(detailListRoute(ocid, slotName))
}
fun NavGraphBuilder.itemNavGraph(
    onNavigationIconClick : () -> Unit,
    onPopBackStack : () -> Unit,
    onItemListButtonClick : (String,String) -> Unit,
    onItemDetailButtonClick : (String) -> Unit
) {
    composable(route = SEARCH_ROUTE) {
        ItemSearchScreenRoot(
            onNavigationIconClick = onNavigationIconClick,
            onSearchCharacterItemClick = onItemDetailButtonClick
        )
    }

    composable(route = detailRoute("{ocid}"),
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
    composable(route = detailListRoute("{ocid}", "{slotName}"),
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
