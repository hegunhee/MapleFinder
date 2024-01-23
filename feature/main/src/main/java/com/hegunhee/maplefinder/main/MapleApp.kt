package com.hegunhee.maplefinder.main

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapleApp(
    mapleAppScaffoldState: MapleAppScaffoldState = rememberMapleAppScaffoldState()
) {
    Text(text = "MapleApp 테스트")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberMapleAppScaffoldState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) : MapleAppScaffoldState {
    return remember(navController,coroutineScope,drawerState) {
        MapleAppScaffoldState(
            coroutineScope = coroutineScope,
            navController = navController,
            drawerState = drawerState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
class MapleAppScaffoldState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val coroutineScope : CoroutineScope,
    val navController : NavHostController,
    val drawerState : DrawerState
) {
    fun navigate(drawerItem : DrawerItem) {
        navController.navigate(drawerItem.navRoute)
        coroutineScope.launch {
            drawerState.close()
        }
    }
}

enum class DrawerItem(
    val titleString : String,
    val icon : ImageVector,
    val navRoute : String
) {

}