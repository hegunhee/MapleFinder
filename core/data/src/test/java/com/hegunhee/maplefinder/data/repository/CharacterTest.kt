package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.api.createResult
import com.hegunhee.maplefinder.data.api.model.OcidResponse
import com.hegunhee.maplefinder.data.datasource.remote.RemoteDataSource
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createAbility
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createBasic
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createHyperstat
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createStat
import com.hegunhee.maplefinder.model.exception.NexonApiException
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CharacterTest {

    @InjectMocks
    private lateinit var sut: DefaultRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Test
    fun givenCharacterNameAndDate_whenGetCharacter_thenReturnCharacter() {
        runBlocking {
            // Given
            val ocid = ""
            val characterName = ""
            val date = ""
            whenever(remoteDataSource.getCharacterOcid(characterName)).thenReturn(OcidResponse(ocid))
            whenever(remoteDataSource.getCharacterBasic(ocid, date)).thenReturn(createBasic())
            whenever(remoteDataSource.getCharacterStat(ocid, date)).thenReturn(createStat())
            whenever(remoteDataSource.getCharacterHyperStat(ocid, date)).thenReturn(createHyperstat())
            whenever(remoteDataSource.getCharacterAbility(ocid, date)).thenReturn(createAbility())

            // When
            val character = sut.getCharacter(characterName, date).getOrThrow()

            // Then
            assertEquals(character.ocid, ocid)

            verify(remoteDataSource).getCharacterOcid(characterName)
            verify(remoteDataSource).getCharacterBasic(ocid, date)
            verify(remoteDataSource).getCharacterStat(ocid, date)
            verify(remoteDataSource).getCharacterHyperStat(ocid, date)
            verify(remoteDataSource).getCharacterAbility(ocid, date)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun givenUnretrivableCharacterName_whenGetCharacter_thenReturnUnretrivableException() {
        runBlocking {
            // Given
            val ocid = ""
            val characterName = "블루배달꾼"
            val date = ""
            whenever(remoteDataSource.getCharacterOcid(characterName)).thenReturn(OcidResponse(ocid))
            whenever(remoteDataSource.getCharacterBasic(ocid, date)).thenThrow(MissingFieldException::class.java)
            whenever(remoteDataSource.getCharacterStat(ocid, date)).thenReturn(createStat())
            whenever(remoteDataSource.getCharacterHyperStat(ocid, date)).thenReturn(createHyperstat())
            whenever(remoteDataSource.getCharacterAbility(ocid, date)).thenReturn(createAbility())

            // When
            val character = createResult { sut.getCharacter(characterName, date).getOrThrow() }

            // Then
            assertThrows(NexonApiException.UnretrivableException::class.java) {
                character.getOrThrow()
            }

            verify(remoteDataSource).getCharacterOcid(characterName)
            verify(remoteDataSource).getCharacterBasic(ocid, date)
            verify(remoteDataSource).getCharacterStat(ocid, date)
            verify(remoteDataSource).getCharacterHyperStat(ocid, date)
            verify(remoteDataSource).getCharacterAbility(ocid, date)
        }
    }

}
