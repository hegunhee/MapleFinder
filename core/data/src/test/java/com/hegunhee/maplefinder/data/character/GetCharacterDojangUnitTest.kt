package com.hegunhee.maplefinder.data.character

import com.hegunhee.maplefinder.data.TestParameter
import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.getMapleApi
import com.hegunhee.maplefinder.data.getMapleOcidApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharacterDojangUnitTest {

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
    fun `get Character Dojang Test`() {
        runBlocking {
            runCatching {
                val ocid = mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME).id
                mapleCharacterApi.getCharacterDojang(
                    ocid = ocid,
                    date = "2024-01-22"
                )
            }.onSuccess { characterDojang ->
                if(characterDojang.characterClass == "엔젤릭버스터") {
                    println(characterDojang.toString())
                    assert(true)
                }else {
                    println(characterDojang.toString())
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}