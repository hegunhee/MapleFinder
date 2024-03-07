package com.hegunhee.maplefinder.item.list

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ItemListScreenRoot(
    viewModel : ItemListViewModel = hiltViewModel(),
    ocid : String,
    popBackStack : () -> Unit
) {
    Button(onClick = popBackStack) {
        Text(text = ocid)
    }
    Text(text = ocid)
}