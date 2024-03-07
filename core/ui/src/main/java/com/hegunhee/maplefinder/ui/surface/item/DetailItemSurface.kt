package com.hegunhee.maplefinder.ui.surface.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.maplefinder.ui.theme.Gray
import com.hegunhee.maplefinder.maplefinder.ui.theme.MapleOrange
import com.hegunhee.maplefinder.maplefinder.ui.theme.Sky
import com.hegunhee.maplefinder.model.Grade
import com.hegunhee.maplefinder.model.character.item.Item
import com.hegunhee.maplefinder.model.character.item.upgrade.StarforceOption

@Composable
fun DetailItemSurface(
    item : Item
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DetailItemHeader(
            slot = item.slot,
            name = item.name,
            soulName = item.soulName,
            scrollUpgradeCount = item.scrollOption.upgradeCount,
            starforce = item.starforceCountOption,
            potentialOptionGrade = item.potentialOption.grade
        )
    }
}

@Composable
fun DetailItemHeader(
    slot : String,
    name : String,
    soulName : String,
    scrollUpgradeCount : String,
    starforce : StarforceOption,
    potentialOptionGrade : Grade,
) {
    HeaderStarForce(
        starCount = starforce.upgardeCount.toInt(),
        maxStarCount = starforce.maxStarCount,
        isStarforceItem = starforce.isStarforceItem,
        isWonderfulScroll = starforce.wonderfulScrollFlag == "사용"
    )
    if(slot == "무기" && soulName.isNotEmpty()) {
        val weaponSoulName = soulName.split(" ")[0] + " "+soulName.split(" ")[1]
        Text(modifier = Modifier.fillMaxWidth(),text = weaponSoulName,fontSize = 20.sp, textAlign = TextAlign.Center)
    }
    val itemName = name + if(scrollUpgradeCount != "0") "(+${scrollUpgradeCount})" else ""
    Text(modifier = Modifier.fillMaxWidth(),text = itemName,fontSize = 20.sp, textAlign = TextAlign.Center)
    if(potentialOptionGrade !is Grade.Unknown) {
        Text(modifier = Modifier.fillMaxWidth(),text = "${potentialOptionGrade.name} 아이템",fontSize = 20.sp,textAlign = TextAlign.Center)
    }
}

@Composable
private fun HeaderStarForce(
    starCount : Int,
    maxStarCount : Int,
    isStarforceItem : Boolean,
    isWonderfulScroll : Boolean
) {
    val starModifier = Modifier.size(12.dp)
    val starColor = if(isWonderfulScroll) {
        Sky
    }else {
        MapleOrange
    }
    if(isStarforceItem) {
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
                    for (i in 16 .. 25) {
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