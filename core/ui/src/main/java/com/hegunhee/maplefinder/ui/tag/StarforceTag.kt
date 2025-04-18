package com.hegunhee.maplefinder.ui.tag

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hegunhee.maplefinder.maplefinder.ui.theme.CloudyOrange
import com.hegunhee.maplefinder.maplefinder.ui.theme.Gray
import com.hegunhee.maplefinder.maplefinder.ui.theme.MapleOrange
import com.hegunhee.maplefinder.maplefinder.ui.theme.Sky
import com.hegunhee.maplefinder.model.character.item.normal.upgrade.StarforceOption

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

@Composable
internal fun StarforceHeader(
    starCount : Int,
    maxStarCount : Int,
    isStarforceItem : Boolean,
    isWonderfulScroll : Boolean
) {
    if(!isStarforceItem) {
        return
    }
    val starModifier = Modifier.size(12.dp)
    val starColor = if(isWonderfulScroll) {
        Sky
    }else {
        MapleOrange
    }
    Column {
        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            for (i in 1.. 15) {
                if (i <= maxStarCount) {
                    StarIcon(
                        modifier = starModifier,
                        tint = if (i <= starCount) {
                            starColor
                        } else {
                            Gray
                        }
                    )
                }
                if (i % 5 == 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }
        if (maxStarCount > 15) {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
                for (i in 16 .. 30) {
                    if (i <= maxStarCount) {
                        StarIcon(
                            modifier = starModifier,
                            tint = if (i <= starCount) {
                                starColor
                            } else {
                                Gray
                            }
                        )
                    }
                    if (i % 5 == 0) {
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun StarIcon(
    modifier : Modifier = Modifier,
    tint : Color = MapleOrange
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Default.Star,
        contentDescription = "star",
        tint = tint
    )
}


@Preview
@Composable
private fun StarforceTagPreview() {
    StarforceTag(option = StarforceOption(true,25,"17", "미적용"))
}

@Preview
@Composable
private fun StarforceHeaderPreview() {
    StarforceHeader(
        starCount = 17,
        maxStarCount = 25,
        isStarforceItem = true,
        isWonderfulScroll = false
    )
}