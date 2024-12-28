package com.hegunhee.maplefinder.item.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hegunhee.maplefinder.item.detail.ItemDetailScreenRoot
import com.hegunhee.maplefinder.item.list.ItemListScreenRoot
import com.hegunhee.maplefinder.item.search.ItemSearchScreenRoot
import com.hegunhee.maplefinder.util.SelectedDateFormatUtil.toDateFormat

const val SEARCH_ROUTE = "Search_Item"

const val DETAIL_ROUTE = "Item_Detail"

fun detailRoute(ocid: String,date: String) : String {
    return "${SEARCH_ROUTE}/${ocid}/${date}"
}

fun detailListRoute(ocid : String,slotName : String,date: String) : String {
    return "${DETAIL_ROUTE}/${ocid}/${slotName}/${date}"
}

fun NavController.navigateItemDetail(ocid : String,date: String) {
    navigate(detailRoute(ocid,date))
}

fun NavController.navigateItemList(ocid : String,slotName: String,date: String) {
    navigate(detailListRoute(ocid, slotName, date))
}
fun NavGraphBuilder.itemNavGraph(
    onNavigationIconClick : () -> Unit,
    onPopBackStack : () -> Unit,
    onItemListButtonClick : (ocid: String,slot: String,date: String) -> Unit,
    onItemDetailButtonClick : (ocid: String,date: String) -> Unit
) {
    composable(route = SEARCH_ROUTE) {
        ItemSearchScreenRoot(
            onNavigationIconClick = onNavigationIconClick,
            onSearchCharacterItemClick = onItemDetailButtonClick
        )
    }

    composable(route = detailRoute("{ocid}","{date}"),
        arguments  = listOf(
            navArgument("ocid") {
                type = NavType.StringType
            }
        )){ navBackStackEntry ->
        val ocid = navBackStackEntry.arguments?.getString("ocid") ?: ""
        val date = navBackStackEntry.arguments?.getString("date").toDateFormat() ?: ""
        ItemDetailScreenRoot(
            ocid = ocid,
            date = date,
            onNavigationIconClick = onNavigationIconClick,
            onItemListButtonClick = onItemListButtonClick
        )
    }
    composable(route = detailListRoute("{ocid}", "{slotName}","{date}"),
        arguments = listOf(
            navArgument("ocid") {
                type = NavType.StringType
            }
        )) { navBackStackEntry ->
        val ocid = navBackStackEntry.arguments?.getString("ocid") ?: ""
        val slotName = navBackStackEntry.arguments?.getString("slotName") ?: ""
        val date = navBackStackEntry.arguments?.getString("date").toDateFormat() ?: ""
        ItemListScreenRoot(
            ocid = ocid,
            slot = slotName,
            date = date,
            popBackStack = onPopBackStack
        )
    }
}
