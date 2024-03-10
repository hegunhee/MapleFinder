package com.hegunhee.maplefinder.ui.dialog

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapleDatePickerDialog(
    initialSelectedDateMills : Long,
    onDateSelected : (String) -> Unit,
    isSelectableDate : (Long) -> Boolean,
    onDismiss : () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDateMills,
        selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return isSelectableDate(utcTimeMillis)
        }
    })
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillsToDate(it)
    } ?: ""

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                if(selectedDate.isNotEmpty()) {
                    onDateSelected(selectedDate)
                    onDismiss()
                }
            }) {
                Text(text = "선택하기")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = "취소")
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )

    }
}

private fun convertMillsToDate(mills : Long) : String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(Date(mills))
}