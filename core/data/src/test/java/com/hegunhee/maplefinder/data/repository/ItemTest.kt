package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.datasource.remote.RemoteDataSource
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createBasic
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createItem
import com.hegunhee.maplefinder.data.repository.ApiResponseProvider.createStat
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ItemTest {

    @InjectMocks
    private lateinit var sut: DefaultRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Test
    fun givenCharacterOcid_whenGetCharacterItem_thenReturnEquippedItem() {
        runBlocking {
            // Given
            val ocid = ""
            val date = ""

            whenever(remoteDataSource.getCharacterBasic(ocid, date)).thenReturn(createBasic())
            whenever(remoteDataSource.getCharacterItem(ocid, date)).thenReturn(createItem())
            whenever(remoteDataSource.getCharacterStat(ocid, date)).thenReturn(createStat())

            // When
            val equippedItem = sut.getCharacterItem(ocid, date).getOrThrow()

            // Then
            assertEquals(equippedItem.ocid, ocid)

            verify(remoteDataSource).getCharacterBasic(ocid, date)
            verify(remoteDataSource).getCharacterItem(ocid, date)
            verify(remoteDataSource).getCharacterStat(ocid, date)

        }
    }

    @Test
    fun givenCharacterOcid_whenGetItemList_thenReturnItemList() {
        runBlocking {
            // Given
            val ocid = ""
            val date = ""

            whenever(remoteDataSource.getCharacterItem(ocid, date)).thenReturn(createItem())

            // When
            val items = sut.getCharacterItemList(ocid, date).getOrThrow()

            // Then
            assertTrue(items.isEmpty())

            verify(remoteDataSource).getCharacterItem(ocid, date)
        }
    }

}
