package com.hegunhee.maplefinder.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import com.hegunhee.maplefinder.character_info.InfoNavGraph
import com.hegunhee.maplefinder.character_info.infoNavGraph
import com.hegunhee.maplefinder.dojang_record.DojangNavGraph
import com.hegunhee.maplefinder.dojang_record.dojangNavGraph
import com.hegunhee.maplefinder.item.ItemNavGraph
import com.hegunhee.maplefinder.item.itemNavGraph
import com.hegunhee.maplefinder.item.navigateItemDetail
import com.hegunhee.maplefinder.item.navigateItemList

@Composable
fun MapleApp(
    mapleAppScaffoldState: MapleAppScaffoldState = rememberMapleAppScaffoldState()
) {
    MapleAppDrawer(
        mapleAppScaffoldState = mapleAppScaffoldState,
        drawerSheetContent = {
            DrawerSheetContent(
                selectedDrawerItem = mapleAppScaffoldState.selectedDrawerItem,
                onClickDrawerItem = mapleAppScaffoldState::navigate
            )
        }
    ) {
        NavHost(
            navController = mapleAppScaffoldState.navController,
            startDestination = InfoNavGraph.infoRoute
        ) {
            dojangNavGraph(
                onNavigationIconClick = mapleAppScaffoldState::openDrawer
            )
            infoNavGraph(
                onNavigationIconClick = mapleAppScaffoldState::openDrawer,
                onItemDetailButtonClick = mapleAppScaffoldState::navigateItemDetail
            )
            itemNavGraph(
                onNavigationIconClick = mapleAppScaffoldState::openDrawer,
                onItemListButtonClick = mapleAppScaffoldState::navigateItemList,
                onItemDetailButtonClick = mapleAppScaffoldState::navigateItemDetail,
                onPopBackStack = mapleAppScaffoldState::popBackStack
            )
        }
    }
}

@Composable
fun rememberMapleAppScaffoldState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
): MapleAppScaffoldState {
    return remember(navController, coroutineScope, drawerState) {
        MapleAppScaffoldState(
            coroutineScope = coroutineScope,
            navController = navController,
            drawerState = drawerState
        )
    }
}

class MapleAppScaffoldState(
    val coroutineScope: CoroutineScope,
    val navController: NavHostController,
    val drawerState: DrawerState
) {
    fun navigate(drawerItem: DrawerItem) {
        navController.navigate(drawerItem.navRoute)
        coroutineScope.launch {
            drawerState.close()
        }
    }

    fun navigateItemDetail(ocid: String) {
        navController.navigateItemDetail(ocid)
    }

    fun navigateItemList(ocid: String, slotName: String) {
        navController.navigateItemList(ocid, slotName)
    }

    fun popBackStack() {
        navController.popBackStack()
    }


    fun openDrawer() {
        coroutineScope.launch {
            drawerState.open()
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

@Composable
fun MapleAppDrawer(
    mapleAppScaffoldState: MapleAppScaffoldState = rememberMapleAppScaffoldState(),
    drawerSheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = mapleAppScaffoldState.drawerState,
        drawerContent = {
            ModalDrawerSheet {
                drawerSheetContent()
            }
        }
    ) {
        content()
    }
}

enum class DrawerGroup {
    Personal, Ranking, Others
}

enum class DrawerItem(
    private val group: DrawerGroup,
    val titleString: String,
    @DrawableRes val iconRes: Int,
    val navRoute: String
) {
    Info(
        group = DrawerGroup.Personal,
        titleString = "캐릭터 정보 조회",
        iconRes = com.hegunhee.maplefinder.designsystem.R.drawable.ic_search_24,
        navRoute = InfoNavGraph.infoRoute
    ),

    ItemInfo(
        group = DrawerGroup.Personal,
        titleString = "착용장비 정보 조회",
        iconRes = com.hegunhee.maplefinder.designsystem.R.drawable.work_gloves,
        navRoute = ItemNavGraph.searchRoute
    ),
    Dojang(
        group = DrawerGroup.Personal,
        titleString = "무릉도장",
        iconRes = com.hegunhee.maplefinder.designsystem.R.drawable.dojangicon,
        navRoute = DojangNavGraph.dojangRoute
    );

    companion object {
        fun ofOrNull(route: String): DrawerItem? {
            return values().firstOrNull { it.navRoute == route }
        }
    }

    val isLastItem: Boolean
        get() = ordinal == DrawerItem.values().lastIndex

    val isGroupLastItem: Boolean
        get() = isLastItem || group != DrawerItem.values()[ordinal + 1].group
}

@Composable
fun DrawerSheetContent(
    selectedDrawerItem: DrawerItem?,
    onClickDrawerItem: (DrawerItem) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        DrawerSheetHeader()
        DrawerItem.values().forEach { drawerItem ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(38.dp),
                        painter = painterResource(id = drawerItem.iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
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
            if (!drawerItem.isLastItem && drawerItem.isGroupLastItem) {
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 28.dp, vertical = 8.dp)
                )
            }
        }
        Divider(
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 8.dp)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Data based on NEXON Open API",
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun DrawerSheetHeader() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = "Maple 정보 검색",
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}
