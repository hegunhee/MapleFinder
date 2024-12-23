package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.api.model.OcidResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterDojangResponse
import com.hegunhee.maplefinder.data.datasource.remote.RemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DojangTest {

    @InjectMocks
    private lateinit var sut : DefaultRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Test
    fun givenCharacterName_whenGetDojangInfo_thenReturnDojangInfo() {
        runBlocking {
            // Given
            val characterName = ""
            val date = ""
            val ocid = ""
            whenever(remoteDataSource.getCharacterOcid(characterName)).thenReturn(OcidResponse(ocid))
            whenever(remoteDataSource.getCharacterDojang(characterName,date)).thenReturn(createDojangInfo(date))

            // When
            val dojangInfo = sut.getCharacterDojangInfo(characterName, date).getOrThrow()

            // Then
            Assert.assertEquals(dojangInfo.characterName,characterName)

            verify(remoteDataSource).getCharacterOcid(characterName)
            verify(remoteDataSource).getCharacterDojang(ocid,date)
        }
    }

    private fun createDojangInfo(date: String) : CharacterDojangResponse {
        return CharacterDojangResponse(
            characterClass = "엔젤릭버스터",
            date = date,
            dojangRecordDate = "2023-12-22T00:00+09:00",
            dojangBestFloor = 30,
            dojangBestTime = 30,
            worldName = "스카니아"
        )
    }

}
