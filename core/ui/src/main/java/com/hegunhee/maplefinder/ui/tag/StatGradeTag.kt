package com.hegunhee.maplefinder.ui.tag

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hegunhee.maplefinder.maplefinder.ui.theme.Gray

@Composable
fun StatGradeTag(statGrade : String) {
    Surface(
        shape = RoundedCornerShape(20),
        border = BorderStroke(width = 1.dp,color = Gray),
        color = Gray
    ) {
        Text(text = "${statGrade}급", color = Color.Black)
    }
}