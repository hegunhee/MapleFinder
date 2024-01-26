package com.hegunhee.maplefinder.ui.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.model.character.CharacterDojang

@Composable
fun DojangSurface(
    characterDojang: CharacterDojang
) {
    Surface(
       shape = RoundedCornerShape(10),
       border = BorderStroke(width = 1.dp, color = Color.Green)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().background(Color.Green),
                text = "무릉 기록 조회",
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "최고 기록"
            )
            Text(text = "${characterDojang.bestFloor}층", fontSize = 20.sp)
            Text(text = "시간")
            Text(text = characterDojang.bestTimeStamp, fontSize = 20.sp)
            Text(text = "기록 날짜")
            Text(text = characterDojang.recordDate,fontSize = 20.sp)

        }
    }
}