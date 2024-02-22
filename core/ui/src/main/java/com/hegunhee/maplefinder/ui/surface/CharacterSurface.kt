package com.hegunhee.maplefinder.ui.surface

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.CharacterAbility
import com.hegunhee.maplefinder.model.character.CharacterBasic
import com.hegunhee.maplefinder.model.character.CharacterHyperStat
import com.hegunhee.maplefinder.model.character.CharacterStat

@Composable
fun CharacterSurface(
    character : Character
) {
    Surface {
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
    Text(characterBasic.toString())
}

@Composable
fun CharacterStat(
    characterStat: CharacterStat,
    characterHyperStat: CharacterHyperStat,
    characterAbility: CharacterAbility
) {
   Text(characterStat.toString())
   Text(characterHyperStat.toString())
   Text(characterAbility.toString())
}