package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.api.model.OcidResponse
import com.hegunhee.maplefinder.data.datasource.remote.RemoteDataSource
import com.hegunhee.maplefinder.model.exception.NexonApiException
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class OcidTest {

    @InjectMocks
    private lateinit var sut: DefaultRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Test
    fun givenCharacterName_whenGetOcid_thenReturnOcid() {
        runBlocking {
            // Given
            val characterName = "엔버는함초롱"
            whenever(remoteDataSource.getCharacterOcid(characterName)).thenReturn(OcidResponse(""))

            // When
            val ocid = sut.getCharacterOcid(characterName).getOrThrow()

            // Then
            assertEquals(ocid.id, "")
            verify(remoteDataSource).getCharacterOcid(characterName)
        }
    }

    @Test
    fun givenEmptyCharacterName_whenGetOcid_thenReturn400() {
        runBlocking {
            // Given
            val characterName = ""
            whenever(remoteDataSource.getCharacterOcid(characterName)).thenThrow(create400Error())

            // When & Then
            val result = sut.getCharacterOcid(characterName)

            // Then
            assertThrows(NexonApiException.InvailedParameterException::class.java) { result.getOrThrow() }
            verify(remoteDataSource).getCharacterOcid(characterName)
        }
    }

    private fun create400Error(): HttpException {
        return HttpException(Response.error<HttpException>(400, ResponseBody.create(null, "")))
    }

}
