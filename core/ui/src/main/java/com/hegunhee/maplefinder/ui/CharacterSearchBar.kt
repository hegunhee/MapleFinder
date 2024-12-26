package com.hegunhee.maplefinder.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterSearchBar(
    searchQuery: String,
    date: String,
    onSearchCharacterClick: (name: String, date: String) -> Unit,
    onQueryChange: (String) -> Unit,
    onDatePickerShowClick: () -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "선택된 날짜 : $date")
            IconButton(onClick = { onDatePickerShowClick() }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "selectDate")
            }
        }
        CharacterNameSearchBar(
            searchQuery = searchQuery,
            searchDate = date,
            onSearchCharacterClick = onSearchCharacterClick,
            onQueryChange = onQueryChange,
            keyboardController = keyboardController
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterNameSearchBar(
    searchQuery: String,
    searchDate: String,
    onSearchCharacterClick: (name: String, date: String) -> Unit,
    onQueryChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    Row {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            onValueChange = onQueryChange,
            label = { Text("캐릭터 이름을 입력해주세요") },
            singleLine = true,
            maxLines = 1,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchCharacterClick(searchQuery, searchDate)
                keyboardController?.hide()
            }),
        )
    }
}