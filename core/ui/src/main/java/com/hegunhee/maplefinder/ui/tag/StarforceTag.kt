package com.hegunhee.maplefinder.ui.tag

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hegunhee.maplefinder.maplefinder.ui.theme.CloudyOrange
import com.hegunhee.maplefinder.maplefinder.ui.theme.Gray
import com.hegunhee.maplefinder.maplefinder.ui.theme.MapleOrange
import com.hegunhee.maplefinder.maplefinder.ui.theme.Sky
import com.hegunhee.maplefinder.model.character.item.upgrade.StarforceOption

@Composable
fun StarforceTag(
    option: StarforceOption
) {
    if(option.upgardeCount == "0"){
        return
    }
    val starColor = if(option.wonderfulScrollFlag == "사용") {
        Sky
    }else {
        MapleOrange
    }
    val backgroundColor = if(option.wonderfulScrollFlag == "사용") {
        Gray
    }else {
        CloudyOrange
    }
    Surface(
        shape = RoundedCornerShape(20),
        border = BorderStroke(width = 1.dp, color = backgroundColor)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(modifier = Modifier.size(20.dp),imageVector = Icons.Default.Star,contentDescription = null,tint = starColor)
            Text(text = option.upgardeCount,color = starColor)
        }
    }
}

@Preview
@Composable
private fun StarforceTagPreview() {
    StarforceTag(option = StarforceOption("17", "미적용"))
}