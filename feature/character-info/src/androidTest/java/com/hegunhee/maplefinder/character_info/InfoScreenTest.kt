package com.hegunhee.maplefinder.character_info

import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class InfoScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun givenInfoScreen_whenClickDateDialog_showDateDialog(){
        composeTestRule.setContent {
            InfoScreen(
                uiState = InfoUiState.Loading,
                searchQuery = "",
                searchDate = "2024-12-25",
                onNavigationIconClick = {},
                onSearchCharacterClick = {name,date ->},
                onQueryChange = {},
                onItemDetailButtonClick = {},
                onDateSelected = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("selectDate")
            .performClick()

        composeTestRule
            .onNodeWithText("선택하기")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("취소")
            .performClick()

        composeTestRule
            .onNodeWithText("캐릭터 정보 검색")
            .assertIsDisplayed()
    }

    @Test
    fun givenInfoScreen_whenDateChange_showChangedDate(){
        composeTestRule.setContent {
            val (selectedDate, onClickDate) = rememberSaveable { mutableStateOf("2024-12-25") }
            InfoScreen(
                uiState = InfoUiState.Loading,
                searchQuery = "",
                searchDate = selectedDate,
                onNavigationIconClick = {},
                onSearchCharacterClick = {name,date ->},
                onQueryChange = {},
                onItemDetailButtonClick = {},
                onDateSelected = onClickDate
            )
        }

        composeTestRule
            .onNodeWithContentDescription("selectDate")
            .performClick()

        composeTestRule
            .onNodeWithText("18", substring = true)
            .performClick()

        composeTestRule
            .onNodeWithText("선택하기", substring = true)
            .performClick()

        composeTestRule
            .onNodeWithText("선택된 날짜 : 2024-12-18")
            .assertIsDisplayed()
    }

}
