package com.hegunhee.maplefinder.ui.surface.stat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.model.character.stat.CharacterStat
import com.hegunhee.maplefinder.model.character.stat.DetailStat

@Composable
fun DetailStat(
    stat : CharacterStat
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "전투력 : ${stat.powerLevel}",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        val listSize = stat.detailStatList.size
        for (i in 0..listSize / 2) {
            Row(modifier = Modifier.fillMaxWidth()) {
                DetailStatItem(item = stat.detailStatList[i])
                if (i == listSize / 2) {
                    if (listSize % 2 != 0) {
                        DetailStatItem(item = stat.detailStatList[i + 1])
                    }
                } else {
                    DetailStatItem(item = stat.detailStatList[i + 1])
                }
            }
        }
    }
}

@Composable
private fun RowScope.DetailStatItem(
    item : DetailStat
) {
    Text(modifier = Modifier.weight(1f),text = "${item.statName} ${item.statValue}", fontSize = 12.sp)
}