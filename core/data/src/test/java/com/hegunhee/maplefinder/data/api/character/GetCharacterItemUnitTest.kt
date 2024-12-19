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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.junit.Before
import org.junit.Test

class GetCharacterItemUnitTest {

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
    fun `get character item has no preset`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                mapleCharacterApi.getCharacterItem(
                    ocid = ocid,
                    date = "2024-01-22"
                )
            }.onSuccess { item ->
                if (item.preset1.isEmpty()) {
                    println(item.toString())
                    assert(true)
                } else {
                    println(item.toString())
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
    fun `get character item has preset`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = "자아정체감").id
                mapleCharacterApi.getCharacterItem(
                    ocid = ocid,
                    date = "2024-03-01"
                )
            }.onSuccess { item ->
                if (item.preset1.isNotEmpty()) {
                    println(item.toString())
                    assert(true)
                } else {
                    println(item.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `get character item is mechanic`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = "세븐").id
                mapleCharacterApi.getCharacterItem(
                    ocid = ocid,
                    date = "2024-03-01"
                )
            }.onSuccess { item ->
                if (item.preset1.isNotEmpty()) {
                    println(item.toString())
                    assert(true)
                } else {
                    println(item.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `get item model class`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = "엔버는함초롱").id
                mapleCharacterApi.getCharacterItem(
                    ocid = ocid,
                    date = "2024-03-01"
                ).toModel()
            }.onSuccess { item ->
                println(item.toString())
                item.itemList.forEach {
                    println(it.getItemStatGrade("덱스"))
                }
                assert(true)
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun `get character item to json`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = "세븐").id
                mapleCharacterApi.getCharacterItem(
                    ocid = ocid,
                    date = "2024-03-01"
                )
            }.onSuccess { item ->
                if (item.preset1.isNotEmpty()) {
                    val list = Json.encodeToString(item.itemEquipmentResponse[0].totalOption)
                    val jsonObject = Json.parseToJsonElement(list)
                    val optionMap = (jsonObject as JsonObject).map {
                        ItemOptionMap(key = it.key, value = it.value.toString().substring(1,it.value.toString().length-1))
                    }.toList()
                    println(optionMap)
                    assert(true)
                } else {
                    println(item.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
    private data class ItemOptionMap(
        val key : String,
        val value : String
    )
}