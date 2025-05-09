package com.hegunhee.maplefinder.data.api.character

import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.api.TestParameter.CHARACTER_NAME
import com.hegunhee.maplefinder.data.api.TestParameter.INQUIRY_DATE
import com.hegunhee.maplefinder.data.di.ApiModule.provideConverterFactory
import com.hegunhee.maplefinder.data.di.ApiModule.provideJson
import com.hegunhee.maplefinder.data.di.ApiModule.provideMapleApi
import com.hegunhee.maplefinder.data.di.ApiModule.provideMapleOcidApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCharacterAbilityUnitTest {

    private lateinit var mapleOcidApi: MapleOcidApi
    private lateinit var mapleCharacterApi: MapleCharacterApi

    @Before
    fun initApi() {
        mapleOcidApi = provideMapleOcidApi(provideConverterFactory(provideJson()))
        mapleCharacterApi = provideMapleApi(provideConverterFactory(provideJson()))
    }

    // 테스트 닉네임 = 엔버는함초롱
    // 테스트 결과 캐릭터명 = 엔젤릭버스터
    // 새로운 규칙 : 2023-12-22 이후 ~ 어제의 날짜까지 조회 가능함
    // 엔버는 함초롱 캐릭은 현재 프리셋이 존재하지 않음 그러므로 프리셋 값이 null으로 떨어져야함
    @Test
    fun givenEmptyPresetCharacterName_whenGetPreset_thenReturnEmptyPreset() {
        runBlocking {
            // Given
            val ocid = mapleOcidApi.getOcid(CHARACTER_NAME).id

            // When
            val ability = mapleCharacterApi.getCharacterAbility(ocid = ocid, date = INQUIRY_DATE)

            // Then
            assertEquals(ability.abilityPreset1, null)
            assertEquals(ability.presetNo, null)
        }
    }

    // 자아정체감 캐릭터는 현재 프리셋이 존재함 그러므로 프리셋이 null값이 아니어야함
    @Test
    fun givenHasPresetCharacterName_whenGetPreset_thenReturnPresets() {
        runBlocking {
            // Given
            val ocid = mapleOcidApi.getOcid(characterName = "자아정체감").id

            // When
            val ability = mapleCharacterApi.getCharacterAbility(ocid = ocid, date = INQUIRY_DATE)

            // Then
            assertTrue(ability.abilityPreset1 != null)
            assertTrue(ability.presetNo != null)
        }
    }

}
