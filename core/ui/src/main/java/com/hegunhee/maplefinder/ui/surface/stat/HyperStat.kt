package com.hegunhee.maplefinder.ui.surface.stat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.model.character.stat.CharacterHyperStat
import com.hegunhee.maplefinder.model.character.stat.HyperStat

@Composable
fun HyperStat(
    hyperStat : CharacterHyperStat
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "하이퍼 스텟",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        val statItems = hyperStat.currentPreset.filter {
            it.statIncrease != null
        }
        statItems.forEach{ hyperStat ->
            HyperStatItem(item = hyperStat)
        }
    }
}

@Composable
fun HyperStatItem(
    item : HyperStat
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 3.dp).padding(end = 5.dp),
            text = "${item.statType} Lv.${item.statLevel}",
        )
        Text(
            text = "${item.statIncrease}"
        )
    }
}