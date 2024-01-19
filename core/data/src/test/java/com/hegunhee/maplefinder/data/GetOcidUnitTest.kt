package com.hegunhee.maplefinder.data

import com.hegunhee.maplefinder.data.api.MapleOcidApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOcidUnitTest {

    private lateinit var mapleOcidApi: MapleOcidApi

    @Before
    fun initApi() {
        mapleOcidApi = getMapleOcidApi()
    }

    // 테스트 닉네임 = 엔버는함초롱
    // 테스트 결과 =05a9d4c40c549bae73b529491cce27d5
    @Test
    fun `get Ocid Test`() {
        runBlocking {
            runCatching {
                mapleOcidApi.getOcid(characterName = TestParameter.CHARACTER_NAME)
            }.onSuccess {
                if(it.id == TestParameter.CHARACTER_OCID) {
                    println(it.id)
                    assert(true)
                }else {
                    assert(false)
                }
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}