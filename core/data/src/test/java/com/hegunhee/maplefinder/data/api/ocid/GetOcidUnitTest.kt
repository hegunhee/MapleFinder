package com.hegunhee.maplefinder.data.api.ocid

import com.hegunhee.maplefinder.data.api.TestParameter
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.di.ApiModule.provideConverterFactory
import com.hegunhee.maplefinder.data.di.ApiModule.provideJson
import com.hegunhee.maplefinder.data.di.ApiModule.provideMapleOcidApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOcidUnitTest {

    private lateinit var mapleOcidApi: MapleOcidApi

    @Before
    fun initApi() {
        mapleOcidApi = provideMapleOcidApi(provideConverterFactory(provideJson()))
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