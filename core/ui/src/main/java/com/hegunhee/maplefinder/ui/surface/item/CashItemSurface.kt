package com.hegunhee.maplefinder.ui.surface.item

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hegunhee.maplefinder.model.ImageUrl
import com.hegunhee.maplefinder.model.character.item.cash.CashEquipmentItem
import com.hegunhee.maplefinder.model.character.item.cash.CashItemCharacter

@Composable
fun CashItemSurface(
    modifier: Modifier = Modifier,
    cashItemCharacter: CashItemCharacter,
    date: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CashItemHeader(
            cashItemCharacter.characterIcon,
            date,
            cashItemCharacter.name
        )
        val items = cashItemCharacter.getEquipmentItems()

        if (items.isEmpty()) {
            Text("캐시 아이템이 존재하지 않습니다.")
            return
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
        ) {
            items(
                items = items,
                key = { it.slot }
            ) { item ->
                CashItem(
                    modifier,
                    item
                )
            }
        }
    }
}

@Composable
private fun CashItemHeader(
    characterImage: ImageUrl,
    date: String,
    name: String,
) {
    AsyncImage(
        model = characterImage,
        modifier = Modifier
            .size(150.dp),
        contentDescription = ""
    )
    Text(name, fontSize = 20.sp, fontStyle = FontStyle.Normal)
    Text("조회 날짜 : $date", fontSize = 15.sp)
}

@Composable
private fun CashItem(
    modifier: Modifier = Modifier,
    item: CashEquipmentItem,
) {
    val (isExtend, onExtendChanged) = remember { mutableStateOf(false) }

    Card(
        modifier.cashItemModifier(!item.isOptionEmpty(),isExtend,onExtendChanged),
        shape = RectangleShape
    ) {
        Column {
            Row {
                AsyncImage(
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(0),
                            color = Color.Black
                        ),
                    model = item.icon,
                    contentDescription = item.name
                )
                Column {
                    Row {
                        Text(item.slot)
                        Spacer(modifier = Modifier.weight(1f))
                        if (!item.isOptionEmpty()) {
                            if (isExtend) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = "isExtend"
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "isNotExtend"
                                )
                            }
                        }
                    }
                    Text(item.name, fontSize = 13.sp)
                }
            }
            if (isExtend) {
                item.option.forEach { option ->
                    Text("${option.optionType} : ${option.optionValue}")
                }
            }
        }
    }
}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
private fun Modifier.cashItemModifier(
    hasOption: Boolean,
    isExpend: Boolean,
    onExpendedChanged: (Boolean) -> Unit,
): Modifier {
    val defaultModifier = this
        .width(200.dp)
        .wrapContentHeight()
        .border(2.dp, Color.Black)
        .padding(5.dp)

    return if (hasOption) {
        defaultModifier
            .clickable { onExpendedChanged(!isExpend) }
    } else {
        defaultModifier
    }
}
