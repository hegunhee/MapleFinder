package com.hegunhee.maplefinder.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapleApp(
    mapleAppScaffoldState: MapleAppScaffoldState = rememberMapleAppScaffoldState()
) {
    MapleAppDrawer(
        mapleAppScaffoldState = mapleAppScaffoldState,
        drawerSheetContent = {
            DrawerSheetContent(selectedDrawerItem = mapleAppScaffoldState.selectedDrawerItem, onClickDrawerItem = mapleAppScaffoldState::navigate)
        }
    ) {
        Text(text = "MapleApp 테스트")
    }
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

    private var _selectedDrawerItem: MutableState<DrawerItem?> = mutableStateOf(null)
    val selectedDrawerItem get() = _selectedDrawerItem.value

    init {
        coroutineScope.launch {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                _selectedDrawerItem.value = destination.route?.let { DrawerItem.ofOrNull(it) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapleAppDrawer(
    mapleAppScaffoldState: MapleAppScaffoldState = rememberMapleAppScaffoldState(),
    drawerSheetContent : @Composable ColumnScope.() -> Unit,
    content : @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = mapleAppScaffoldState.drawerState,
        drawerContent = { ModalDrawerSheet {
            drawerSheetContent()
        }}
    ) {
        content()
    }

}

enum class DrawerItem(
    val titleString : String,
    val icon : ImageVector,
    val navRoute : String
) {

    Item(
        titleString = "",
        icon = Icons.Default.Search,
        navRoute = ""
    );
    companion object {
        fun ofOrNull(route: String): DrawerItem? {
            return values().firstOrNull { it.navRoute == route }
        }
    }

    val isLastItem: Boolean
        get() = ordinal == DrawerItem.values().lastIndex
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheetContent(
    selectedDrawerItem : DrawerItem?,
    onClickDrawerItem : (DrawerItem) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        DrawerItem.values().forEach { drawerItem ->
            NavigationDrawerItem(
                icon = {
                    Icon(imageVector = drawerItem.icon, contentDescription = null)
                },
                label = {
                    Text(drawerItem.titleString)
                },
                selected = selectedDrawerItem == drawerItem,
                onClick = {
                    onClickDrawerItem(drawerItem)
                },
                modifier = Modifier.padding(vertical = 10.dp)
            )
            if (!drawerItem.isLastItem) {
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 28.dp, vertical = 8.dp)
                )
            }
        }
    }
}