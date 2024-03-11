package com.hegunhee.maplefinder.ui.surface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.model.exception.NexonApiException

@Composable
fun ErrorSurface(
    exception : Throwable
) {
    when(exception) {
        is NexonApiException -> {
            HttpErrorSurface(code = exception.code, message = exception.message)
        }
        else -> {
            UnknownErrorSurface(exception)
        }
    }
}

@Composable
private fun HttpErrorSurface(code : Int,message : String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(modifier = Modifier.size(50.dp),imageVector = Icons.Default.Build, contentDescription = "message")
        Text(message, fontSize = 18.sp)
        Text("[에러코드 : ${code}]",fontSize = 13.sp)
    }
}
@Composable
private fun UnknownErrorSurface(
    exception : Throwable
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(modifier = Modifier.size(50.dp),imageVector = Icons.Default.Build, contentDescription = "message")
        Text("알수없는 에러가 발생했습니다.",fontSize = 18.sp)
        Text(exception.toString(),fontSize = 13.sp)
    }
}