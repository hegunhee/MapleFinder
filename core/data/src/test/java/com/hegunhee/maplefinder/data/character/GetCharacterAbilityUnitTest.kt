package com.hegunhee.maplefinder.data.character

import com.hegunhee.maplefinder.data.TestParameter
import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.getMapleApi
import com.hegunhee.maplefinder.data.getMapleOcidApi
import com.hegunhee.maplefinder.data.mapper.toModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharacterAbilityUnitTest {

    private lateinit var mapleOcidApi: MapleOcidApi
    private lateinit var mapleCharacterApi : MapleCharacterApi

    @Before
    fun initApi() {
        mapleOcidApi = getMapleOcidApi()
        mapleCharacterApi = getMapleApi()
    }

    // 테스트 닉네임 = 엔버는함초롱
    // 테스트 결과 캐릭터명 = 엔젤릭버스터
    // 새로운 규칙 : 2023-12-22 이후 ~ 어제의 날짜까지 조회 가능함
    // 엔버는 함초롱 캐릭은 현재 프리셋이 존재하지 않음 그러므로 프리셋 값이 null으로 떨어져야함
    @Test
    fun `get ability if has no preset`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                mapleCharacterApi.getCharacterAbility(
                    ocid = ocid,
                    date = "2024-01-22"
                )
            }.onSuccess { characterAbility ->
                if(characterAbility.abilityPreset1 == null) {
                    println(characterAbility.toString())
                    assert(true)
                }else {
                    println(characterAbility.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    // 자아정체감 캐릭터는 현재 프리셋이 존재함 그러므로 프리셋이 null값이 아니어야함

    @Test
    fun `get ability if has preset`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = "자아정체감").id
                mapleCharacterApi.getCharacterAbility(
                    ocid = ocid,
                    date = "2024-01-22"
                )
            }.onSuccess { characterAbility ->
                if(characterAbility.abilityPreset1 != null) {
                    println(characterAbility.toString())
                    assert(true)
                }else {
                    println(characterAbility.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `get ability model if has no preset`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                mapleCharacterApi.getCharacterAbility(
                    ocid = ocid,
                    date = "2024-01-22"
                ).toModel()
            }.onSuccess { characterAbility ->
                if(characterAbility.abilityInfo == null) {
                    println(characterAbility.toString())
                    assert(true)
                }else {
                    println(characterAbility.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `get ability model if has preset`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = "자아정체감").id
                mapleCharacterApi.getCharacterAbility(
                    ocid = ocid,
                    date = "2024-01-22"
                ).toModel()
            }.onSuccess { characterAbility ->
                if(characterAbility.abilityInfo != null) {
                    println(characterAbility.toString())
                    assert(true)
                }else {
                    println(characterAbility.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}