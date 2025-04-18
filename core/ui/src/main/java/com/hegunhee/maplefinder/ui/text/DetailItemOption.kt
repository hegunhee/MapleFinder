package com.hegunhee.maplefinder.ui.text

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hegunhee.maplefinder.maplefinder.ui.theme.Cyan
import com.hegunhee.maplefinder.maplefinder.ui.theme.MapleOrange
import com.hegunhee.maplefinder.maplefinder.ui.theme.PurpleWhite
import com.hegunhee.maplefinder.maplefinder.ui.theme.Sky

@Composable
internal fun DetailOptionText(
    key : String,
    value : Int,
    baseOptionValue : Int?,
    addOptionValue : Int?,
    scrollOptionValue : Int?,
    starforceOptionValue : Int?
) {
    val optionList : List<Int?> = listOf(baseOptionValue,addOptionValue,scrollOptionValue,starforceOptionValue)
    val colorList : List<Color> = listOf(LocalContentColor.current, Cyan, PurpleWhite, MapleOrange)
    Row {
        if(addOptionValue == null && scrollOptionValue == null && starforceOptionValue ==null) {
            Text(text = "$key : +$value")
        }else {
            Text(text = "$key : +$value",color = Sky)
            Text(text = " (")
            for(i in optionList.indices) {
                if(optionList[i] != null) {
                    if(i == 0) {
                        Text("${optionList[i]}",color = colorList[i])
                    }else {
                        Text("+${optionList[i]}",color = colorList[i])
                    }
                }
            }
            Text(text = ")")
        }
    }
}

@Composable
internal fun ItemSoulText(
    soulName : String,
    soulOption : String
) {
    Text("$soulName 적용",color = MapleOrange)
    Spacer(modifier = Modifier.size(3.dp))
    Text(soulOption)
}

@Preview
@Composable
private fun DetailOptionTextPreview() {
    Row {
        DetailOptionText(
            key = "힘",
            value = 30,
            baseOptionValue = 10,
            addOptionValue = 3,
            scrollOptionValue = 7,
            starforceOptionValue = 10
        )
    }

}

@Preview
@Composable
private fun ItemSoulTextPreview() {
    Column {
        ItemSoulText(soulName = "위대한 루시드의 소울", soulOption = "공격력 3%")
    }
}