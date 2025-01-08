package com.hegunhee.maplefinder.ui.tag

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hegunhee.maplefinder.maplefinder.ui.theme.ExceptionRed
import com.hegunhee.maplefinder.model.character.item.normal.ItemOption

@Composable
internal fun ExceptionOption (
    exceptionOption : List<ItemOption>
) {
    Text("익셉셔널 강화",color = ExceptionRed)
    exceptionOption.forEach {
        Text("${it.key}: +${it.value}")
    }
}

@Preview
@Composable
private fun ExceptionOptionPreview() {
    val itemOption = listOf<ItemOption>(
        ItemOption(key = "올스텟",value = "20",),
        ItemOption(key = "공격력",value = "10")
    )
    ExceptionOption(exceptionOption = itemOption)
}