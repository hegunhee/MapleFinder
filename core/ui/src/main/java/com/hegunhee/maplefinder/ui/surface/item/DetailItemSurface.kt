package com.hegunhee.maplefinder.ui.surface.item

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hegunhee.maplefinder.maplefinder.ui.theme.Cyan
import com.hegunhee.maplefinder.maplefinder.ui.theme.MapleOrange
import com.hegunhee.maplefinder.model.Grade
import com.hegunhee.maplefinder.model.ImageUrl
import com.hegunhee.maplefinder.model.character.item.Item
import com.hegunhee.maplefinder.model.character.item.ItemOption
import com.hegunhee.maplefinder.model.character.item.upgrade.CubeOption
import com.hegunhee.maplefinder.model.character.item.upgrade.ScrollOption
import com.hegunhee.maplefinder.model.character.item.upgrade.StarforceOption
import com.hegunhee.maplefinder.ui.space.NormalLineSpace
import com.hegunhee.maplefinder.ui.tag.DetailItemCubeOption
import com.hegunhee.maplefinder.ui.tag.ExceptionOption
import com.hegunhee.maplefinder.ui.tag.StarforceHeader
import com.hegunhee.maplefinder.ui.text.DetailOptionText
import com.hegunhee.maplefinder.ui.text.ItemSoulText

@Composable
fun DetailItemSurface(
    scrollState: ScrollState,
    item : Item
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        DetailItemHeader(
            slot = item.slot,
            name = item.name,
            soulName = item.soulName,
            scrollUpgradeCount = item.scrollOption.upgradeCount,
            starforce = item.starforceCountOption,
            potentialOptionGrade = item.potentialOption.grade
        )
        NormalLineSpace()
        DetailItemIcon(
            icon = item.icon,
            grade = item.potentialOption.grade,
            baseLevel = item.baseOption.baseLevel
        )
        NormalLineSpace()
        DetailItemOption(
            part = item.part,
            scrollOption = item.scrollOption,
            cuttableCount = item.cuttableCount,
            totalOption = item.totalOption,
            baseOption = item.baseOption.options,
            addOption = item.addOption,
            scrollUpgradeOption = item.etcOption,
            starforceOption = item.starforceOption
        )
        DetailItemBottom(
            potentialOption = item.potentialOption,
            additionalOption = item.additionalOption,
            exceptionalOption = item.exceptionalOption,
            hasSoulItem = item.soulName.isNotEmpty(),
            soulName = item.soulName,
            soulOption = item.soulOption,
            isWonderfulScrollUse = item.starforceCountOption.wonderfulScrollFlag == "사용"
        )
    }
}

@Composable
private fun DetailItemHeader(
    slot : String,
    name : String,
    soulName : String,
    scrollUpgradeCount : String,
    starforce : StarforceOption,
    potentialOptionGrade : Grade,
) {
    StarforceHeader(
        starCount = starforce.upgardeCount.toInt(),
        maxStarCount = starforce.maxStarCount,
        isStarforceItem = starforce.isStarforceItem,
        isWonderfulScroll = starforce.wonderfulScrollFlag == "사용"
    )
    if(slot == "무기" && soulName.isNotEmpty()) {
        val weaponSoulName = soulName.split(" ")[0] + " "+soulName.split(" ")[1]
        Text(modifier = Modifier.fillMaxWidth(),text = weaponSoulName,fontSize = 20.sp, textAlign = TextAlign.Center,color = Cyan)
    }
    val itemName = name + if(scrollUpgradeCount != "0") "(+${scrollUpgradeCount})" else ""
    Text(modifier = Modifier.fillMaxWidth(),text = itemName,fontSize = 20.sp, textAlign = TextAlign.Center)
    if(potentialOptionGrade !is Grade.Unknown) {
        Text(modifier = Modifier.fillMaxWidth(),text = "${potentialOptionGrade.name} 아이템",fontSize = 20.sp,textAlign = TextAlign.Center)
    }
}

@Composable
private fun DetailItemIcon(
    icon : ImageUrl,
    grade : Grade,
    baseLevel : Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(0),
                    color = Color(grade.color)
                ) ,model = icon, contentDescription = icon)
        Spacer(modifier = Modifier.size(5.dp))
        Spacer(modifier = Modifier
            .size(5.dp)
            .background(MapleOrange))
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = "REQ LEV : $baseLevel", color = MapleOrange)
    }
}

@Composable
private fun DetailItemOption(
    part : String,
    scrollOption: ScrollOption,
    cuttableCount : String,
    totalOption : List<ItemOption>,
    baseOption : List<ItemOption>,
    addOption : List<ItemOption>,
    scrollUpgradeOption : List<ItemOption>,
    starforceOption : List<ItemOption>
) {
    Text(text = "장비 분류 : $part")
    totalOption.forEach {
        val key = it.key
        val value = it.value.toInt()
        DetailOptionText(
            key = key,
            value = value,
            baseOptionValue = baseOption.find { it.key == key }?.value?.toIntOrNull(),
            addOptionValue = addOption.find { it.key == key }?.value?.toIntOrNull(),
            scrollOptionValue = scrollUpgradeOption.find { it.key == key }?.value?.toIntOrNull(),
            starforceOptionValue = starforceOption.find { it.key == key }?.value?.toIntOrNull()
        )
    }
    if(scrollOption.upgradeCount.toInt() + scrollOption.recoverableCount.toInt() != 0) {
        Row {
            Text("업그레이드 가능 횟수 : ${scrollOption.upgradableCount}")
            Text(" (복구 가능 횟수 : ${scrollOption.recoverableCount})",color = MapleOrange)
        }
        if(scrollOption.goldenHammerFlag == "적용") {
            Text("황금망치 재련 적용")
        }
    }
    if(cuttableCount != "255") {
        Text("가위 사용 가능 횟수 : $cuttableCount",color = MapleOrange)
    }
}

@Composable
private fun DetailItemBottom(
    potentialOption : CubeOption,
    additionalOption : CubeOption,
    exceptionalOption : List<ItemOption>,
    hasSoulItem : Boolean,
    soulName : String,
    soulOption : String,
    isWonderfulScrollUse : Boolean
) {
    if(potentialOption.grade !is Grade.Unknown) {
        NormalLineSpace()
        DetailItemCubeOption(option = potentialOption)
    }
    if(additionalOption.grade !is Grade.Unknown) {
        NormalLineSpace()
        DetailItemCubeOption(cubeType = "에디셔널",option = additionalOption)
    }
    if(exceptionalOption.isNotEmpty()) {
        NormalLineSpace()
        ExceptionOption(exceptionalOption)
    }
    if(isWonderfulScrollUse) {
        NormalLineSpace()
        Text("놀라운 장비 강화 주문서가 사용되었습니다",color = MapleOrange)
    }
    if(hasSoulItem) {
        NormalLineSpace()
        ItemSoulText(soulName = soulName, soulOption = soulOption)
    }
}