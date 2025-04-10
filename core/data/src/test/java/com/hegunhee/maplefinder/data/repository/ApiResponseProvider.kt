package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.api.model.character.CharacterBasicResponse
import com.hegunhee.maplefinder.data.api.model.character.item.cash.CashItemResponse
import com.hegunhee.maplefinder.data.api.model.character.item.normal.ItemResponse
import com.hegunhee.maplefinder.data.api.model.character.item.normal.TitleResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterAbilityResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterHyperStatResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterStatResponse

object ApiResponseProvider {

    fun createBasic(): CharacterBasicResponse {
        return CharacterBasicResponse(
            jobName = "엔젤릭버스터",
            jobLevel = "6",
            exp = 1,
            expRate = "1.0",
            gender = "male",
            guildName = "",
            image = "",
            level = 12,
            name = "엔버는 함초롱",
            date = "2023-12-22T00:00+09:00",
            worldName = "스카니아"
        )
    }

    fun createStat(): CharacterStatResponse {
        return CharacterStatResponse(
            jobName = "엔젤릭버스터",
            date = "2023-12-22T00:00+09:00",
            detailStatList = listOf(),
            remainAp = 0
        )
    }

    fun createHyperstat(): CharacterHyperStatResponse {
        return CharacterHyperStatResponse(
            jobName = "엔젤릭버스터",
            date = "2023-12-22T00:00+09:00",
            hyperStatPreset1 = emptyList(),
            hyperStatPreset1RemainPoint = 0,
            hyperStatPreset2 = emptyList(),
            hyperStatPreset2RemainPoint = 0,
            hyperStatPreset3 = emptyList(),
            hyperStatPreset3RemainPoint = 0,
            remainHyperStat = 0,
            currentPresetNum = "1"
        )
    }

    fun createAbility(): CharacterAbilityResponse {
        return CharacterAbilityResponse(
            abilityGrade = "",
            abilityInfo = emptyList(),
            abilityPreset1 = null,
            abilityPreset2 = null,
            abilityPreset3 = null,
            date = "2023-12-22T00:00+09:00",
            presetNo = 0,
            remainFame = 0
        )
    }

    fun createItem(): ItemResponse {
        return ItemResponse(
            characterClass = "",
            characterGender = "",
            date = "2023-12-22T00:00+09:00",
            dragonEquipment = emptyList(),
            itemEquipmentResponse = emptyList(),
            preset1 = emptyList(),
            preset2 = emptyList(),
            preset3 = emptyList(),
            mechanicEquipment = emptyList(),
            presetNo = 1,
            titleResponse = TitleResponse("", "", "", "", "")
        )
    }

    fun createCashItem(): CashItemResponse {
        return CashItemResponse(
            characterClass = "엔젤릭버스터",
            characterGender = "여",
            characterLookMode = null,
            presetNo = null,
            equipmentBase = listOf(),
            preset1 = listOf(),
            preset2 = listOf(),
            preset3 = listOf(),
            additionalBase = listOf(),
            additionalPreset1 = listOf(),
            additionalPreset2 = listOf(),
            additionalPreset3 = listOf(),
            date = null
        )
    }
}
