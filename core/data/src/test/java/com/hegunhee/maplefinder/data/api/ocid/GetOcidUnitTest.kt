package com.hegunhee.maplefinder.data.api.ocid

import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.api.TestParameter.CHARACTER_NAME
import com.hegunhee.maplefinder.data.api.TestParameter.CHARACTER_OCID
import com.hegunhee.maplefinder.data.api.createResult
import com.hegunhee.maplefinder.data.di.ApiModule.provideConverterFactory
import com.hegunhee.maplefinder.data.di.ApiModule.provideJson
import com.hegunhee.maplefinder.data.di.ApiModule.provideMapleOcidApi
import com.hegunhee.maplefinder.model.exception.NexonApiException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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
    fun givenCharacterName_whenGetOcid_thenReturnOcid() {
        runBlocking {
            // Given

            // When
            val ocid = mapleOcidApi.getOcid(characterName = CHARACTER_NAME).id

            // Then
            assertEquals(ocid, CHARACTER_OCID)
        }
    }

    @Test
    fun givenEmptyName_whenGetOcid_thenReturn400_INVAILED_PARAMETER() {
        runBlocking {
            createResult {
                // Given
                val emptyCharacterName = ""

                // When
                mapleOcidApi.getOcid(emptyCharacterName)
            }.onFailure { exception ->
                // Then
                assertEquals(exception, NexonApiException.InvailedParameterException)
            }
        }
    }

}
