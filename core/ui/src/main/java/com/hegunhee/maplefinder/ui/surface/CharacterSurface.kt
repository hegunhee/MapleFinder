package com.hegunhee.maplefinder.ui.surface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.stat.CharacterAbility
import com.hegunhee.maplefinder.model.character.CharacterBasic
import com.hegunhee.maplefinder.model.character.stat.CharacterHyperStat
import com.hegunhee.maplefinder.model.character.stat.CharacterStat
import com.hegunhee.maplefinder.ui.surface.parameter.PreviewParameter
import com.hegunhee.maplefinder.ui.surface.parameter.PreviewParameter.createAbility
import com.hegunhee.maplefinder.ui.surface.parameter.PreviewParameter.createCharacter
import com.hegunhee.maplefinder.ui.surface.parameter.PreviewParameter.createHyperStat
import com.hegunhee.maplefinder.ui.surface.parameter.PreviewParameter.createStat
import com.hegunhee.maplefinder.ui.surface.stat.AbilityPreset
import com.hegunhee.maplefinder.ui.surface.stat.DetailStat
import com.hegunhee.maplefinder.ui.surface.stat.HyperStat
import com.hegunhee.maplefinder.ui.tag.WorldTag

@Composable
fun CharacterSurface(
    character : Character,
    onItemDetailButtonClick: (String) -> Unit
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
            CharacterDetailButton(
                ocid = character.ocid,
                onItemDetailButtonClick = onItemDetailButtonClick
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
private fun ColumnScope.CharacterStat(
    characterStat: CharacterStat,
    characterHyperStat: CharacterHyperStat,
    characterAbility: CharacterAbility
) {
    DetailStat(stat = characterStat)
    HyperStat(hyperStat = characterHyperStat)
    val (abilityNum, onAbilityNumChange) = remember { mutableIntStateOf(characterAbility.presetNo) }
    AbilityPreset(currentNum = abilityNum, abilityInfo = characterAbility.abilityInfo,abilityPresetList = characterAbility.abilityPresetList, onAbilityClickButton = onAbilityNumChange)
}

@Composable
private fun CharacterDetailButton(
    ocid : String,
    onItemDetailButtonClick : (String) -> Unit
) {
    Button(modifier = Modifier.fillMaxWidth(),onClick = { onItemDetailButtonClick(ocid) }) {
        Text(modifier = Modifier.fillMaxWidth(),text = "착용 장비 정보 탐색", textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
private fun CharacterSurfacePreview() {
    CharacterSurface(
        character = createCharacter(),
        onItemDetailButtonClick = {  }
    )
}

@Preview
@Composable
private fun CharacterStatPreview() {
    Column {
        CharacterStat(
            characterStat = createStat(),
            characterAbility = createAbility(),
            characterHyperStat = createHyperStat(),
        )
    }
}
