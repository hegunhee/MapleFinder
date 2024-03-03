package com.hegunhee.maplefinder.data.character

import com.hegunhee.maplefinder.data.TestParameter
import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.getMapleApi
import com.hegunhee.maplefinder.data.getMapleOcidApi
import com.hegunhee.maplefinder.data.mapper.toModel
import com.hegunhee.maplefinder.model.character.Character
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class GetCharacterModelClassUnitTest {

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
    @Test
    fun `get character model class test (with async)`() {
        runBlocking {
            val date = "2024-02-20"
            coroutineScope {
                runCatching {
                    val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                    val basicInfo = async {mapleCharacterApi.getCharacterBasic(ocid,date).toModel() }
                    val statInfo = async { mapleCharacterApi.getCharacterStat(ocid, date).toModel() }
                    val hyperStatInfo = async { mapleCharacterApi.getCharacterHyperStat(ocid,date).toModel() }
                    val abilityInfo = mapleCharacterApi.getCharacterAbility(ocid,date).toModel()
                    Character(
                        ocid = ocid,
                        basic = basicInfo.await(),
                        stat = statInfo.await(),
                        hyperStat = hyperStatInfo.await(),
                        ability = abilityInfo
                    )
                }.onSuccess { character ->
                    if(character.job == "엔젤릭버스터") {
                        println(character.toString())
                        assert(true)
                    }else {
                        println(character.toString())
                        assert(false)
                    }
                }.onFailure {
                    println(it.message)
                    assert(false)
                }
            }
        }
    }

    // async를 이용해 비동기로 작동시킬시 시간이 얼마나 걸리는지 테스트
    // 걸린 시간 : 976.282700ms

    @OptIn(ExperimentalTime::class)
    @Test
    fun `get character by sync`() {
        runBlocking {
            val date = "2024-02-20"
            runCatching {
                measureTimedValue {
                    val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                    val basicInfo = mapleCharacterApi.getCharacterBasic(ocid, date).toModel()
                    val statInfo = mapleCharacterApi.getCharacterStat(ocid, date).toModel()
                    val hyperStatInfo = mapleCharacterApi.getCharacterHyperStat(ocid, date).toModel()
                    val abilityInfo = mapleCharacterApi.getCharacterAbility(ocid, date).toModel()
                    Character(
                        ocid = ocid,
                        basic = basicInfo,
                        stat = statInfo,
                        hyperStat = hyperStatInfo,
                        ability = abilityInfo
                    )
                }
            }.onSuccess { time ->
                println("걸린시간 : ${time.duration}")
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    // async를 이용해 비동기로 작동시킬시 시간이 얼마나 걸리는지 테스트
    // 걸린 시간 : 905.119900ms
    @OptIn(ExperimentalTime::class)
    @Test
    fun `get character by async`() {
        runBlocking {
            val date = "2024-02-20"
            runCatching {
                measureTimedValue {
                    val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                    val basicInfo = async { mapleCharacterApi.getCharacterBasic(ocid, date).toModel() }
                    val statInfo = async { mapleCharacterApi.getCharacterStat(ocid, date).toModel() }
                    val hyperStatInfo = async { mapleCharacterApi.getCharacterHyperStat(ocid, date).toModel() }
                    val abilityInfo = mapleCharacterApi.getCharacterAbility(ocid, date).toModel()
                    Character(
                        ocid = ocid,
                        basic = basicInfo.await(),
                        stat = statInfo.await(),
                        hyperStat = hyperStatInfo.await(),
                        ability = abilityInfo
                    )
                }
            }.onSuccess { time ->
                println("걸린시간 : ${time.duration}")
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}