package com.hegunhee.maplefinder.data.mapper

import com.hegunhee.maplefinder.data.api.model.CharacterDojangResponse
import com.hegunhee.maplefinder.model.character.CharacterDojang
import com.hegunhee.maplefinder.util.TimeUtil

fun CharacterDojangResponse.toCharacterDojang(
    characterName : String
) : CharacterDojang {
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

private fun Int.toTimeStamp() : String{
    val second = this % 60
    val minute = this / 60
    return "${minute}분 ${second}초"
}