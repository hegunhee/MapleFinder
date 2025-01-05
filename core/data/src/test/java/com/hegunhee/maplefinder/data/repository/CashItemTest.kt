package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.api.model.OcidResponse
import com.hegunhee.maplefinder.data.datasource.remote.RemoteDataSource
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createBasic
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createCashItem
import com.hegunhee.maplefinder.model.character.item.cash.LookupMode
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.invoke.MethodHandles.Lookup

@RunWith(MockitoJUnitRunner::class)
class CashItemTest {

    @InjectMocks
    private lateinit var sut: DefaultRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Test
    fun givenCharacterOcid_whenGetCharacterCashItem_thenReturnCashItemCharacter() {
        runBlocking {
            // Given
            val characaterName = ""
            val ocid = ""
            val date = ""

            whenever(remoteDataSource.getCharacterOcid(characaterName)).thenReturn(OcidResponse(ocid))
            whenever(remoteDataSource.getCharacterBasic(ocid, date)).thenReturn(createBasic())
            whenever(remoteDataSource.getCharacterCashItem(ocid, date)).thenReturn(createCashItem())

            // When
            val cashItemCharacter = sut.getCharacterCashItem(ocid, date).getOrThrow()

            // Then
            Assert.assertEquals(cashItemCharacter.characterLookMode,LookupMode.NORMAL)

            verify(remoteDataSource).getCharacterOcid(characaterName)
            verify(remoteDataSource).getCharacterBasic(ocid, date)
            verify(remoteDataSource).getCharacterCashItem(ocid, date)
        }
    }
}
