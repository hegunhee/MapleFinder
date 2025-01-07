package com.hegunhee.maplefinder.ui.surface.item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
        ) {
            items(
                items = items,
                key = { it.slot }
            ) { item ->
                CashItem(item)
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
    item: CashEquipmentItem,
) {
    Card(
        modifier = Modifier
            .size(width = 200.dp, height = 100.dp)
            .border(2.dp, Color.Black)
            .padding(5.dp),
        shape = RectangleShape
    ) {
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
                Text(item.slot)
                Text(item.name)
            }

        }
    }
}
