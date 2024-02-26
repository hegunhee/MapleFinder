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
import java.text.DecimalFormat

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
    val detailStatList = detailStatList.filterNot {
        filterStat.contains(it.statName)
    }.filterNot {
        it.statName.contains("AP")
    }.map {
        it.copy(statName =it.statName,statValue = it.statValue.toStatFormat()).toModel()
    }
    val powerLevel = this.detailStatList.find { it.statName == "전투력" }?.statValue ?: "0"

    return CharacterStat(
        jobName = jobName,
        powerLevel = powerLevelToFormat(powerLevel = powerLevel.toInt()),
        detailStatList = detailStatList,
        remainAp = remainAp,
        date = date
    )
}

private fun String.toStatFormat() : String {
    return if(this.contains(".")) {
        "${this.split(".")[0]}%"
    } else {
        val format = DecimalFormat("#,###")
        format.format(this.toInt()) ?: ""
    }
}

private fun powerLevelToFormat(powerLevel : Int) : String {
    val formattedValue = DecimalFormat("#,####").format(powerLevel) ?: ""
    val parts = formattedValue.split(",")
    return when (parts.size) {
        2 -> "${parts[0]}만 ${parts[1]}"
        3 -> "${parts[0]}억 ${parts[1]}만 ${parts[2]}"
        else -> formattedValue
    }
}

private val filterStat = listOf<String>(
    "최소 스텟공격력",
    "스탠스",
    "방어력",
    "이동속도",
    "점프력",
    "전투력" // 전투력은 기존의 response에서 추출해서 model 클래스에 기재하므로 삭제
)

fun CharacterHyperStatResponse.toModel() : CharacterHyperStat {
    val hyperStatList = listOf<List<HyperStat>>(hyperStatPreset1,hyperStatPreset2,hyperStatPreset3).map {
        it.toModel()
    }
    return CharacterHyperStat(
        jobName = jobName,
        currentPreset = hyperStatList[currentPresetNum.toInt()-1],
        hyperStatPresetList = hyperStatList,
        remainHyperStat = remainHyperStat,
        currentPresetNum =currentPresetNum
    )
}

fun CharacterAbilityResponse.toModel() : CharacterAbility {
    return CharacterAbility(
        abilityGrade = abilityGrade.toGrade(),
        abilityInfo = abilityInfo.toModel(),
        abilityPresetList = listOf(abilityPreset1,abilityPreset2,abilityPreset3).map { it?.toModel() },
        presetNo = presetNo ?: 1,
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