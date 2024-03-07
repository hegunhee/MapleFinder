package com.hegunhee.maplefinder.ui.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hegunhee.maplefinder.model.Grade
import com.hegunhee.maplefinder.model.character.item.CharacterEquipmentItem
import com.hegunhee.maplefinder.model.character.item.Item
import com.hegunhee.maplefinder.ui.tag.CubeOptionTag
import com.hegunhee.maplefinder.ui.tag.StarforceTag
import com.hegunhee.maplefinder.ui.tag.StatGradeTag

@Composable
fun CharacterEquipmentItemSurface(
    characterEquipmentItem: CharacterEquipmentItem,
    onDetailItemClick: (String,String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            CharacterHeader(characterBasic = characterEquipmentItem.basic)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(items = characterEquipmentItem.equipmentItem.itemList, key = {it.slot}) { item ->
                    EquipmentItem(ocid = characterEquipmentItem.ocid,item = item,mainStat = characterEquipmentItem.mainStat, onDetailItemClick)
                }
            }
        }
    }
}

@Composable
private fun EquipmentItem(
    ocid : String,
    item : Item,
    mainStat : String,
    onDetailItemClick : (String,String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Max)) {
        Spacer(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .size(1.dp)
            .background(Color.Black))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onDetailItemClick(ocid,item.slot) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "아이템 자세히보기")
            Text(text = "자세히보기", fontSize = 12.sp)
        }
        Text(text = item.name,fontSize = 15.sp)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {
                AsyncImage(modifier = Modifier
                    .size(40.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(0),
                        color = Color(item.potentialOption.grade.color)
                    ),model = item.icon, contentDescription = item.name)
                Spacer(modifier = Modifier.size(5.dp))
            }
            Spacer(modifier = Modifier.size(5.dp))
            Column {
                Text(text = item.part,fontSize = 12.sp)
                Row {
                    StarforceTag(option = item.starforceCountOption)
                    val statGrade = item.getItemStatGrade(mainStat)
                    if(statGrade.isNotEmpty()) {
                        Spacer(modifier = Modifier.size(3.dp))
                        StatGradeTag(statGrade = statGrade)
                    }
                }
            }
        }
        item.totalOption.forEach { option ->
            key(option.key) {
                Text(text = "${option.key} : ${option.value}",fontSize = 10.sp)
            }
        }

        if(item.potentialOption.grade !is Grade.Unknown) {
            CubeOptionTag(cubeType = "잠재", cubeOption = item.potentialOption)
        }
        if(item.additionalOption.grade !is Grade.Unknown) {
            CubeOptionTag(cubeType = "에디", cubeOption = item.additionalOption)
        }
    }
}
