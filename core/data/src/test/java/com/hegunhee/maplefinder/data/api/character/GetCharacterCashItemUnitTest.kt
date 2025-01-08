package com.hegunhee.maplefinder.data.api.character

import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.hegunhee.maplefinder.data.api.TestParameter
import com.hegunhee.maplefinder.data.api.TestParameter.CHARACTER_NAME
import com.hegunhee.maplefinder.data.di.ApiModule.provideConverterFactory
import com.hegunhee.maplefinder.data.di.ApiModule.provideJson
import com.hegunhee.maplefinder.data.di.ApiModule.provideMapleApi
import com.hegunhee.maplefinder.data.di.ApiModule.provideMapleOcidApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCharacterCashItemUnitTest {

    private lateinit var mapleOcidApi: MapleOcidApi
    private lateinit var mapleCharacterApi: MapleCharacterApi

    @Before
    fun initApi() {
        mapleOcidApi = provideMapleOcidApi(provideConverterFactory(provideJson()))
        mapleCharacterApi = provideMapleApi(provideConverterFactory(provideJson()))
    }

    @Test
    fun givenCharacterName_whenGetCashItem_thenReturnCashItem() {
        runBlocking {
            // Given
            val ocid = mapleOcidApi.getOcid(CHARACTER_NAME).id

            // When
            val cashItemResponse =
                mapleCharacterApi.getCharacterCashItem(ocid, TestParameter.INQUIRY_DATE)

            // Then
            assertEquals(cashItemResponse.characterClass, "엔젤릭버스터")
        }
    }

    @Test
    fun givenDateNull_whenGetCashItem_thenReturnDateEmpty() {
        runBlocking {
            // Given
            val ocid = mapleOcidApi.getOcid(CHARACTER_NAME).id

            // When
            val cashItemResponse = mapleCharacterApi.getCharacterCashItem(ocid, null)

            // Then
            assertEquals(cashItemResponse.date, null)
        }
    }

    @Test
    fun givenAngelicBusterCharacter_whenGetAngelicBusterItem_thenReturnAdditional() {
        runBlocking {
            // Given
            val angelicBuster1stName = "휴씨엔버"
            val ocid = mapleOcidApi.getOcid(angelicBuster1stName).id

            // When
            val cashItemResponse = mapleCharacterApi.getCharacterCashItem(ocid, "2025-01-01")

            // Then
            assertTrue(cashItemResponse.additionalBase.isNotEmpty())
        }
    }

    @Test
    fun givenNotAdditionalItemCharacter_whenGetItem_thenReturnEmptyAdditional() {
        runBlocking {
            // Given
            val name = "꾸장"
            val ocid = mapleOcidApi.getOcid(name).id

            // When
            val cashItemResponse = mapleCharacterApi.getCharacterCashItem(ocid, "2025-01-01")

            // Then
            assertTrue(cashItemResponse.additionalBase.isEmpty())
        }
    }

}

