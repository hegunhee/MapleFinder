package com.hegunhee.maplefinder.ui.surface.stat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.model.character.stat.AbilityInfo
import com.hegunhee.maplefinder.model.character.stat.AbilityPreset

@Composable
fun AbilityPreset(
    currentNum : Int,
    abilityInfo : List<AbilityInfo>,
    abilityPresetList : List<AbilityPreset?>,
    onAbilityClickButton : (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "어빌리티",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Row{
            abilityPresetList.forEachIndexed { index, abilityPreset ->
                if(abilityPreset == null) {
                    AbilityEmptyButton()
                }else {
                    AbilityButton(isClicked = currentNum == index, abilityNum = index,onAbilityClickButton)
                }
            }
        }
        Spacer(modifier = Modifier.size(5.dp))
        abilityPresetList[currentNum]?.abilityInfo?.let { abilityInfoList ->
            Ability(ability = abilityInfoList)
        } ?: run {
            Ability(ability = abilityInfo)
        }
        Spacer(modifier = Modifier.size(10.dp))
    }

}

@Composable
private fun RowScope.AbilityButton(
    isClicked : Boolean,
    abilityNum : Int,
    onAbilityClickButton: (Int) -> Unit
) {
    Button(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .weight(1f),
        onClick = { onAbilityClickButton(abilityNum) },
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text(
            text = (abilityNum + 1).toString(),
            color = if(isClicked) {
                Color.Yellow
            }else {
                Color.White
            })
    }
}

@Composable
private fun RowScope.AbilityEmptyButton() {
    Button(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .weight(1f),
        onClick = { },
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text(text = "Empty")
    }
}

@Composable
private fun Ability(
    ability : List<AbilityInfo>
) {
    ability.forEach { abilityInfo ->
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .background(Color(abilityInfo.grade.color)),text = abilityInfo.value, color = Color.White, textAlign = TextAlign.Center)
    }
}