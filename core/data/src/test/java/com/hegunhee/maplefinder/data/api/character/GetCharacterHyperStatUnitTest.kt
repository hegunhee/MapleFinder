package com.hegunhee.maplefinder.data.api.character

import com.hegunhee.maplefinder.data.api.TestParameter
import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.di.ApiModule.provideConverterFactory
import com.hegunhee.maplefinder.data.di.ApiModule.provideJson
import com.hegunhee.maplefinder.data.di.ApiModule.provideMapleApi
import com.hegunhee.maplefinder.data.di.ApiModule.provideMapleOcidApi
import com.hegunhee.maplefinder.data.mapper.toModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharacterHyperStatUnitTest {

    private lateinit var mapleOcidApi: MapleOcidApi
    private lateinit var mapleCharacterApi : MapleCharacterApi

    @Before
    fun initApi() {
        mapleOcidApi = provideMapleOcidApi(provideConverterFactory(provideJson()))
        mapleCharacterApi = provideMapleApi(provideConverterFactory(provideJson()))
    }

    // 테스트 닉네임 = 엔버는함초롱
    // 테스트 결과 캐릭터명 = 엔젤릭버스터
    // 새로운 규칙 : 2023-12-22 이후 ~ 어제의 날짜까지 조회 가능함
    @Test
    fun `get hyper stat info test`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                mapleCharacterApi.getCharacterHyperStat(
                    ocid = ocid,
                    date = "2024-01-22"
                )
            }.onSuccess { characterHyperStat ->
                if(characterHyperStat.jobName == "엔젤릭버스터") {
                    println(characterHyperStat.toString())
                    assert(true)
                }else {
                    println(characterHyperStat.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `get hyper stat model test`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                mapleCharacterApi.getCharacterHyperStat(
                    ocid = ocid,
                    date = "2024-01-22"
                ).toModel()
            }.onSuccess { characterHyperStat ->
                if(characterHyperStat.jobName == "엔젤릭버스터") {
                    println(characterHyperStat.toString())
                    assert(true)
                }else {
                    println(characterHyperStat.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}