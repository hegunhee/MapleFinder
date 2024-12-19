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

class GetCharacterBasicUnitTest {

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
    fun `get character basic info test`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                mapleCharacterApi.getCharacterBasic(
                    ocid = ocid,
                    date = "2024-02-20"
                )
            }.onSuccess { characterBasic ->
                if(characterBasic.jobName == "엔젤릭버스터") {
                    println(characterBasic.toString())
                    assert(true)
                }else {
                    println(characterBasic.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `get character basic model test`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                mapleCharacterApi.getCharacterBasic(
                    ocid = ocid,
                    date = "2024-02-20"
                ).toModel()
            }.onSuccess { characterBasic ->
                if(characterBasic.jobName == "엔젤릭버스터") {
                    println(characterBasic.toString())
                    assert(true)
                }else {
                    println(characterBasic.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}