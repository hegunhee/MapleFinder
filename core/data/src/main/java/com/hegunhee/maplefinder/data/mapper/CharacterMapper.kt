package com.hegunhee.maplefinder.data.mapper

import com.hegunhee.maplefinder.data.R
import com.hegunhee.maplefinder.data.api.model.character.CharacterAbilityResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterBasicResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterDojangResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterHyperStatResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterStatResponse
import com.hegunhee.maplefinder.data.api.model.character.DetailStat
import com.hegunhee.maplefinder.data.api.model.character.HyperStat
import com.hegunhee.maplefinder.data.api.model.character.toModel
import com.hegunhee.maplefinder.model.Grade
import com.hegunhee.maplefinder.model.World
import com.hegunhee.maplefinder.model.character.CharacterAbility
import com.hegunhee.maplefinder.model.character.CharacterBasic
import com.hegunhee.maplefinder.model.character.CharacterDojang
import com.hegunhee.maplefinder.model.character.CharacterHyperStat
import com.hegunhee.maplefinder.model.character.CharacterStat
import com.hegunhee.maplefinder.util.TimeUtil

fun CharacterDojangResponse.toCharacterDojang(
    characterName: String
): CharacterDojang {
    return CharacterDojang(
        characterName = characterName,
        characterClass =characterClass,
        worldName = worldName,
        recordDate = TimeUtil.apiFormatToUiFormat(dojangRecordDate),
        bestFloor = dojangBestFloor,
        bestTimeStamp = dojangBestTime.toTimeStamp(),
        bestTime = dojangBestTime
    )
}

fun CharacterBasicResponse.toModel(): CharacterBasic {
    return CharacterBasic(
        name = name,
        level = level,
        jobName = jobName,
        jobLevel = jobLevel,
        world = worldName.toWorld(),
        image = image,
        gender = gender,
        exp = exp,
        expRate = expRate,
        guildName = guildName,
        date = date
    )
}

fun CharacterStatResponse.toModel(): CharacterStat {
    return CharacterStat(
        jobName = jobName,
        detailStatList = detailStatList.map(DetailStat::toModel),
        remainAp = remainAp,
        date = date
    )
}

fun CharacterHyperStatResponse.toModel() : CharacterHyperStat {
    val hyperStatList = listOf<List<HyperStat>>(hyperStatPreset1,hyperStatPreset2,hyperStatPreset3).map {
        it.toModel()
    }
    return CharacterHyperStat(
        jobName = jobName,
        hyperStatPresetList = hyperStatList,
        remainHyperStat = remainHyperStat,
        currentPresetNum =currentPresetNum
    )
}

fun CharacterAbilityResponse.toModel() : CharacterAbility {
    return CharacterAbility(
        abilityGrade = abilityGrade.toGrade(),
        abilityInfo = abilityInfo.toModel(),
        abilityPreset1 = abilityPreset1?.toModel(),
        abilityPreset2 = abilityPreset2?.toModel(),
        abilityPreset3 = abilityPreset3?.toModel(),
        presetNo = presetNo,
        remainFame = remainFame
    )
}

internal val mapleWorldList = listOf<World>(
    World("아케인", R.drawable.arcane),
    World("베라",R.drawable.bera),
    World("크로아",R.drawable.croa),
    World("엘리시움",R.drawable.elysium),
    World("루나",R.drawable.luna),
    World("노바",R.drawable.noba),
    World("오로라",R.drawable.orora),
    World("레드",R.drawable.red),
    World("스카니아",R.drawable.scania),
    World("유니온",R.drawable.union),
    World("제니스",R.drawable.zenith),
    World("리부트",R.drawable.reboot),
    World("리부트2",R.drawable.reboot),
)

private val mapleMWorldNameMap = mapleWorldList.associateBy { it.name }

fun String.toWorld() : World {
    return mapleMWorldNameMap[this] ?: World(this,R.drawable.ic_default_server_mark_24)
}

internal fun String.toGrade() : Grade {
    return when(this) {
        "레어" -> Grade.Rare
        "에픽" -> Grade.Epic
        "유니크" -> Grade.Unique
        "레전드리" -> Grade.Legendary
        else -> Grade.Unknown
    }
}

private fun Int.toTimeStamp(): String {
    val second = this % 60
    val minute = this / 60
    return "${minute}분 ${second}초"
}