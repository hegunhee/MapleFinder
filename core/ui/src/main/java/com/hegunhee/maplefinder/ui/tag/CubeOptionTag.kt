package com.hegunhee.maplefinder.ui.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.maplefinder.ui.theme.Gray
import com.hegunhee.maplefinder.model.Grade
import com.hegunhee.maplefinder.model.character.item.normal.upgrade.CubeOption

@Composable
internal fun CubeOptionTag(
    cubeType : String = "잠재",
    cubeOption: CubeOption
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val fontColor = Color(cubeOption.grade.color)
        val backgroundColor = Gray
        Text(text = cubeType)
        Text(modifier = Modifier.background(backgroundColor),text = cubeOption.firstOption,color = fontColor,fontSize = 10.sp)
        Text(modifier = Modifier.background(backgroundColor),text = cubeOption.secondOption,color = fontColor,fontSize = 10.sp)
        if(cubeOption.thirdOption.isNotEmpty()) {
            Text(modifier = Modifier.background(backgroundColor),text = cubeOption.thirdOption,color = fontColor,fontSize = 10.sp)
        }
    }
}

@Composable
internal fun DetailItemCubeOption(
    cubeType : String = "",
    option : CubeOption,
) {
    Text("${cubeType}잠재 옵션 (${option.grade.name})",color = Color(option.grade.color))
    Spacer(modifier = Modifier.size(5.dp))
    Text(option.firstOption)
    Text(option.secondOption)
    if(option.thirdOption != "") {
        Text(option.thirdOption)
    }
}

@Preview
@Composable
private fun CubeOptionTagPreview() {
    CubeOptionTag(cubeOption = CubeOption(Grade.Legendary,"보공 40%","보공 40%","보공 40%"))
}

@Preview
@Composable
private fun DetailItemCubeOptionPreview() {
    val option = CubeOption(grade = Grade.Legendary,"보공 40%","보공 40%","보공 40%")
    DetailItemCubeOption(option = option)
}