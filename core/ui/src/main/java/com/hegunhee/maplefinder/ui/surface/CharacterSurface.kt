package com.hegunhee.maplefinder.ui.surface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.CharacterAbility
import com.hegunhee.maplefinder.model.character.CharacterBasic
import com.hegunhee.maplefinder.model.character.CharacterHyperStat
import com.hegunhee.maplefinder.model.character.CharacterStat
import com.hegunhee.maplefinder.ui.tag.WorldTag

@Composable
fun CharacterSurface(
    character : Character
) {
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Column {
            CharacterHeader(characterBasic = character.basic)
            CharacterStat(
                characterStat = character.stat,
                characterHyperStat = character.hyperStat,
                characterAbility = character.ability
            )
        }
    }
}

@Composable
fun CharacterHeader(
    characterBasic : CharacterBasic
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(model = characterBasic.image,modifier = Modifier.size(100.dp), contentDescription = "")
        Column {
            Spacer(modifier = Modifier.size(10.dp))
            Row {
                Text(characterBasic.name, fontSize = 20.sp, fontStyle = FontStyle.Normal,color = Color.Black)
                Spacer(modifier = Modifier.size(5.dp))
                WorldTag(world = characterBasic.world)
            }
            Text(text = "Lv.${characterBasic.level} | ${characterBasic.jobName}")
        }
    }
}

@Composable
fun CharacterStat(
    characterStat: CharacterStat,
    characterHyperStat: CharacterHyperStat,
    characterAbility: CharacterAbility
) {
    DetailStat(stat = characterStat)
    HyperStat(hyperStat = characterHyperStat)
    val (abilityNum, onAbilityNumChange) = remember { mutableIntStateOf(characterAbility.presetNo) }
    AbilityPreset(currentNum = abilityNum, abilityInfo = characterAbility.abilityInfo,abilityPresetList = characterAbility.abilityPresetList, onAbilityClickButton = onAbilityNumChange)
}