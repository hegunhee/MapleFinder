package com.hegunhee.maplefinder.data.mapper

import com.hegunhee.maplefinder.data.api.model.character.CharacterAbilityResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterBasicResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterDojangResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterHyperStatResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterStatResponse
import com.hegunhee.maplefinder.data.api.model.character.DetailStat
import com.hegunhee.maplefinder.data.api.model.character.HyperStat
import com.hegunhee.maplefinder.data.api.model.character.toModel
import com.hegunhee.maplefinder.model.character.CharacterAbility
import com.hegunhee.maplefinder.model.character.CharacterBasic
import com.hegunhee.maplefinder.model.character.CharacterDojang
import com.hegunhee.maplefinder.model.character.CharacterHyperStat
import com.hegunhee.maplefinder.model.character.CharacterStat
import com.hegunhee.maplefinder.util.TimeUtil

fun CharacterDojangResponse.toCharacterDojang(
    characterName: String
): CharacterDojang {
    val response = this
    return CharacterDojang(
        characterName = characterName,
        characterClass = response.characterClass,
        worldName = response.worldName,
        recordDate = TimeUtil.apiFormatToUiFormat(response.dojangRecordDate),
        bestFloor = response.dojangBestFloor,
        bestTimeStamp = response.dojangBestTime.toTimeStamp(),
        bestTime = response.dojangBestTime
    )
}

fun CharacterBasicResponse.toModel(): CharacterBasic {
    val response = this
    return CharacterBasic(
        name = response.name,
        jobName = response.jobName,
        jobLevel = response.jobLevel,
        worldName = response.worldName,
        image = response.image,
        gender = response.gender,
        exp = response.exp,
        expRate = response.expRate,
        guildName = response.guildName,
        date = response.date
    )
}

fun CharacterStatResponse.toModel(): CharacterStat {
    val response = this
    return CharacterStat(
        jobName = response.jobName,
        detailStatList = detailStatList.map(DetailStat::toModel),
        remainAp = remainAp,
        date = date
    )
}

fun CharacterHyperStatResponse.toModel() : CharacterHyperStat {
    val response = this
    val hyperStatList = listOf<List<HyperStat>>(this.hyperStatPreset1,this.hyperStatPreset2,this.hyperStatPreset3).map {
        it.toModel()
    }
    return CharacterHyperStat(
        jobName = response.jobName,
        hyperStatPresetList = hyperStatList,
        remainHyperStat = response.remainHyperStat,
        currentPresetNum = response.currentPresetNum
    )
}

fun CharacterAbilityResponse.toModel() : CharacterAbility {
    val response = this
    return CharacterAbility(
        abilityGrade = abilityGrade,
        abilityInfo = abilityInfo.toModel(),
        abilityPreset1 = abilityPreset1?.toModel(),
        abilityPreset2 = abilityPreset2?.toModel(),
        abilityPreset3 = abilityPreset3?.toModel(),
        presetNo = presetNo,
        remainFame = remainFame
    )
}

private fun Int.toTimeStamp(): String {
    val second = this % 60
    val minute = this / 60
    return "${minute}분 ${second}초"
}